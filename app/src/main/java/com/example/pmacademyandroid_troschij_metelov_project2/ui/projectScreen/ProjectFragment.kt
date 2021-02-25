package com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ProjectScreenBinding
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.UserProfileScreenFragmentBinding
import com.example.pmacademyandroid_troschij_metelov_project2.tools.State
import com.example.pmacademyandroid_troschij_metelov_project2.tools.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.tools.navigator.BaseFragment
import javax.inject.Inject


class ProjectFragment(private val userProfile : UserProfile, private val projectName : String) : BaseFragment(R.layout.project_screen) {
    companion object {
        fun newInstance(userProfile : UserProfile, projectName : String) = ProjectFragment(userProfile,projectName)
    }

    @Inject
    lateinit var viewModel : ProjectScreenViewModel
    private lateinit var binding: ProjectScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProjectScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDi()
        setupLiveDataListeners()
        viewModel.getContent(projectName,"OlexiyTr")
    }

    private fun setupDi() {
        val app = requireActivity().application as ClientApp
        app.getComponent().inject(this)
    }

    private fun setupLiveDataListeners(){
        viewModel.readMeLiveData.observe(viewLifecycleOwner){
            updateReadMe(it)
        }
        viewModel.issuesLiveData.observe(viewLifecycleOwner){
        }
        viewModel.contributorsLiveData.observe(viewLifecycleOwner){
        }
    }

    private fun updateReadMe(state : State<String, Int>){
        when(state){
            is State.Data ->{
                binding.tvReadme.text = state.data
            }
            is State.Loading -> {
            }
            is State.Unauthorized -> navigation.showLoginScreen()
            is State.Error -> binding.tvReadme.text = "ERROR"
        }
    }









}