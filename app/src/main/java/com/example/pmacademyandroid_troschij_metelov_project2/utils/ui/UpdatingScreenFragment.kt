package com.example.pmacademyandroid_troschij_metelov_project2.utils.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmacademyandroid_troschij_metelov_project2.ClientApp
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.UpdateTokenScreenFragmentBinding
import com.example.pmacademyandroid_troschij_metelov_project2.utils.UpdatingState
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator.BaseFragment
import com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator.Navigator

import javax.inject.Inject

class UpdatingScreenFragment(private val code: String) :
    BaseFragment(
        R.layout.update_token_screen_fragment
    ) {

    private val navigator by lazy {
        Navigator(requireActivity().supportFragmentManager, R.id.fragment_container)
    }

    private lateinit var binding: UpdateTokenScreenFragmentBinding

    @Inject
    lateinit var viewModel: UpdatingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UpdateTokenScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDi()
        setupLivaDataListener()
        viewModel.updateToken(code)
    }

    private fun setupDi() {
        val app = requireActivity().application as ClientApp
        app.getComponent().inject(this)
    }

    private fun setupLivaDataListener() {
        viewModel.updateStatusLiveData.observe(viewLifecycleOwner, {
            it?.let { updatingState ->
                when (updatingState) {
                    UpdatingState.LOADING -> showProgressBar()
                    UpdatingState.COMPLETED -> openUserFragment()
                    UpdatingState.ERROR -> showError()
                }
            }
        })
    }

    private fun showProgressBar() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvError.visibility = View.GONE
        }
    }

    private fun openUserFragment() {
        navigator.showUserScreen(UserProfile.UserCurrent)
    }

    private fun showError() {
        binding.apply {
            progressBar.visibility = View.GONE
            tvError.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newInstance(code: String) = UpdatingScreenFragment(code)
    }
}