package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.contributorsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.USER_KEY
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ContributorsScreenBinding
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ClientAppState
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator.BaseFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.ProjectScreenViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.UserProject
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContributorsScreenFragment(private val userProject: UserProject) :
    BaseFragment(R.layout.contributors_screen) {

    companion object {
        fun newInstance(userProject: UserProject) =
            ContributorsScreenFragment(userProject).apply {
                arguments = Bundle().apply {
                    putSerializable(USER_KEY, userProject)
                }
            }
    }

    private lateinit var binding: ContributorsScreenBinding

    @Inject
    lateinit var viewModel: ProjectScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContributorsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val contributorsAdapter = ContributorsAdapter {
        navigation.showUserScreen(UserProfile.UserPublic(it.name))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDi()
        setupLiveDataListeners()
        setupRecycler()
        viewModel.getContributors(userProject.repoName, userProject.userName)
    }

    private fun setupDi() {
        val clientApp = requireActivity().application as ClientApp
        clientApp.getComponent().inject(this)
    }

    private fun setupRecycler() {
        binding.apply {
            rvContributors.adapter = contributorsAdapter
            rvContributors.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupLiveDataListeners() {
        viewModel.contributorsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ClientAppState.Data -> updateContributors(it.data)
            }
        }
    }

    private fun updateContributors(pagingData: PagingData<UserResponse>) {
        viewModel.scopeProjectScreen.launch {
            contributorsAdapter.submitData(pagingData)
        }
    }

}
