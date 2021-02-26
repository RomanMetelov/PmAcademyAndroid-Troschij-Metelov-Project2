package com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.readmeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ReadmeScreenBinding
import com.example.pmacademyandroid_troschij_metelov_project2.tools.ClientAppState
import com.example.pmacademyandroid_troschij_metelov_project2.tools.navigator.BaseFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.ProjectScreenViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.UserProject
import javax.inject.Inject

class ReadMeScreenFragment(private val userProject: UserProject) : BaseFragment(R.layout.readme_screen) {

    private lateinit var binding : ReadmeScreenBinding

    @Inject
    lateinit var viewModel: ProjectScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ReadmeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDi()
        setupLiveDataListeners()
        viewModel.getReadMe(userProject.userName,userProject.repoName)
    }

    private fun setupDi(){
        val app = requireActivity().application as ClientApp
        app.getComponent().inject(this)
    }

    private fun setupLiveDataListeners(){
        viewModel.readMeLiveData.observe(viewLifecycleOwner){
            when(it){
                is ClientAppState.Data -> binding.tvReadMe.text = it.data
            }
        }
    }

    companion object{
        fun newInstance(userProject : UserProject) =
            ReadMeScreenFragment(userProject).apply{
                arguments = Bundle().apply{
                    putSerializable("USER_KEY",userProject)
                }
            }
    }
}