package com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.loginScreenFeature.LoginScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.ProjectFragment
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.UserProject
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ui.UpdatingScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.ui.UserProfileScreenFragment

class Navigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val container: Int
) {
    companion object {
        private const val PROJECT_SCREEN_KEY = "PROJECT_SCREEN_KEY"
        private const val ISSUE_SCREEN_KEY = "ISSUE_SCREEN_KEY"
    }

    fun showUserScreen(userProfile: UserProfile) {
        fragmentManager.beginTransaction()
            .replace(container, UserProfileScreenFragment.newInstance(userProfile))
            .commit()
    }

    fun showLoginScreen() {
        fragmentManager.beginTransaction()
            .add(container, LoginScreenFragment.newInstance())
            .commit()
    }

    fun showUpdatingFragment(code: String) {
        fragmentManager
            .beginTransaction()
            .add(container, UpdatingScreenFragment.newInstance(code))
            .commit()
    }

    fun showProjectScreen(userProject : UserProject) {
        fragmentManager.beginTransaction()
            .add(container, ProjectFragment.newInstance(userProject))
            .addToBackStack(PROJECT_SCREEN_KEY)
            .commit()
    }

    fun showIssueScreen() {
        fragmentManager.beginTransaction()
            .add(container, IssueScreenFragment.newInstance())
            .addToBackStack(ISSUE_SCREEN_KEY)
            .commit()
    }
}