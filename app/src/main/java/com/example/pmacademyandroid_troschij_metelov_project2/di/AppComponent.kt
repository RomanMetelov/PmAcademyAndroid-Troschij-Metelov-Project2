package com.example.pmacademyandroid_troschij_metelov_project2.di

import android.content.Context
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.ProjectFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.updatingScreen.UpdatingScreenFragment
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.UserProfileScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class])
interface AppComponent {
    val context : Context

    fun inject(fragment: UserProfileScreenFragment)
    fun inject(fragment: UpdatingScreenFragment)
    fun inject(fragment: ProjectFragment)
}
