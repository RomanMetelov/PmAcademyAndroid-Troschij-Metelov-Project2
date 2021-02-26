package com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.UserProfileScreenFragmentBinding
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.UsersSearchActivity
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ClientAppState
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator.BaseFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.UserProject
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.adapter.ReposAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileScreenFragment(private val userProfile: UserProfile) :
    BaseFragment(R.layout.user_profile_screen_fragment) {
    companion object {
        fun newInstance(userProfile: UserProfile) = UserProfileScreenFragment(userProfile)
    }

    @Inject
    lateinit var viewModel: UserProfileScreenViewModel

    private lateinit var binding: UserProfileScreenFragmentBinding

    private val reposAdapter = ReposAdapter {
        navigation.showProjectScreen(UserProject(binding.userGroup.getName(), it.name))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserProfileScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDi()
        setupLiveDataListeners()
        setupRecyclerProfile()
        setupListeners()

        viewModel.getUser()
    }

    private fun setupRecyclerProfile() {
        binding.rvRepos.apply {
            adapter = reposAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        viewModel.getRepos()
    }


    private fun setupDi() {
        val app = requireActivity().application as ClientApp
        app.getComponent().inject(this)
    }

    private fun setupLiveDataListeners() {
        viewModel.userProfile = userProfile
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) {
            updateUser(it)
        }
        viewModel.reposLiveData.observe(viewLifecycleOwner) {
            viewModel.scope.launch {
                reposAdapter.submitData(it)
            }
        }
    }

    private fun updateUser(clientAppState: ClientAppState<UserResponse, Int>) {
        when (clientAppState) {
            is ClientAppState.Loading -> binding.ivProgressBar.isVisible = true
            is ClientAppState.Unauthorized -> navigation.showLoginScreen()
            is ClientAppState.Error -> binding.userGroup.setName(getString(clientAppState.error))
            is ClientAppState.Data -> {
                binding.userGroup.apply {
                    setImage(clientAppState.data.avatar_url)
                    setName(clientAppState.data.name)
                    binding.ivProgressBar.isVisible = false
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val intent = Intent(activity, UsersSearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
        }
    }
}