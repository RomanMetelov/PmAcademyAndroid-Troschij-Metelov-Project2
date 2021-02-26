package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.USER_KEY
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ProjectScreenBinding
import com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class ProjectFragment : BaseFragment(R.layout.project_screen) {

    private lateinit var binding: ProjectScreenBinding
    private lateinit var viewPager: ProjectScreenViewPager
    private lateinit var userProject: UserProject

    companion object {
        fun newInstance(userProject: UserProject) =
            ProjectFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER_KEY, userProject)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProjectScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupTabLayout()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userProject = it.getSerializable(USER_KEY) as UserProject
        }
    }

    private fun setupAdapter() {
        viewPager = ProjectScreenViewPager(this, userProject)
        binding.viewPager.adapter = viewPager
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = Page.values()[position].toString()
        }.attach()
    }
}