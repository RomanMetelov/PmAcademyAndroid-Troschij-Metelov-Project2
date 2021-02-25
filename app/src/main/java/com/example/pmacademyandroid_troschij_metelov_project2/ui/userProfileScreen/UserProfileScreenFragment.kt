package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.UserGroup
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.UserProfileScreenFragmentBinding
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.tools.State
import com.example.pmacademyandroid_troschij_metelov_project2.tools.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.tools.navigator.BaseFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.ReposAdapter
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.UserProfileAdapter
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
    private val profileAdapter = UserProfileAdapter {
        when (it) {
            is UserGroup -> navigation.showUserScreen(UserProfile.UserPublic(it.getName()))
            is AppCompatTextView -> navigation.showProjectScreen(
                viewModel.userProfile,
                it.text.toString()
            )
        }
    }

    private val reposAdapter = ReposAdapter() {
        when (it) {
            is UserGroup -> navigation.showUserScreen(UserProfile.UserPublic(it.getName()))
            is AppCompatTextView -> navigation.showProjectScreen(
                viewModel.userProfile,
                it.text.toString()
            )
        }
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

        viewModel.getContent()
    }

    private fun setupRecyclerProfile() {
        binding.rvRepos.adapter = reposAdapter
        binding.rvRepos.setHasFixedSize(true)
        binding.rvRepos.isNestedScrollingEnabled = false
        binding.rvRepos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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

    private fun updateUser(state: State<UserResponse, Int>) {
        when (state) {
            is State.Loading -> {
            }
            is State.Unauthorized -> navigation.showLoginScreen()
            is State.Error -> binding.userGroup.setName(getString(state.error))
            is State.Data -> {
                binding.userGroup.apply {
                    setImage(state.data.avatar_url)
                    setName(state.data.name)
                }
            }
        }
    }

    /*private fun updateRepos(state: State<List<ReposResponse>, Int>) {
        when (state) {
            is State.Loading -> {
            }
            is State.Unauthorized -> navigation.showLoginScreen()
            is State.Error -> {
                profileAdapter.submitList(listOf(UserProfileRecyclerState.Error(getString(state.error))))
            }
            is State.Content -> {
                profileAdapter.submitList(state.data.map { UserProfileRecyclerState.Repos(it) })
            }
        }
    } */

}