package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.contributorsScreen.ContributorsScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.readmeScreen.ReadMeScreenFragment

class ProjectScreenViewPager(fragment: Fragment, private val userProject: UserProject) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return Page.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (Page.values()[position]) {
            Page.CONTRIBUTORS -> ContributorsScreenFragment.newInstance(userProject)
            Page.README -> ReadMeScreenFragment.newInstance(userProject)
            Page.ISSUES -> IssueScreenFragment.newInstance()
        }
    }
}