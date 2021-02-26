package com.example.pmacademyandroid_troschij_metelov_project2.di

import android.content.Context
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.contributorsScreen.ContributorsScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.issuesScreen.IssuesScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.readmeScreen.ReadMeScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.updatingScreen.ui.UpdatingScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreen.ui.UserProfileScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class])
interface AppComponent {
    val context : Context


    fun inject(fragment: UserProfileScreenFragment)
    fun inject(fragment: UpdatingScreenFragment)
    fun inject(fragment: ReadMeScreenFragment)
    fun inject(fragment : ContributorsScreenFragment)
    fun inject(fragment : IssueScreenFragment)
    fun inject(fragment : IssuesScreenFragment)
}
