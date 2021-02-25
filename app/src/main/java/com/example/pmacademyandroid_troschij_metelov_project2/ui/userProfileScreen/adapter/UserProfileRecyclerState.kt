package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter

import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.ReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.UserResponse

sealed class UserProfileRecyclerState {
    class Error(val errorText: String) : UserProfileRecyclerState()
    class Repos(val repos: ReposResponse) : UserProfileRecyclerState()
    class User(val user: UserResponse) : UserProfileRecyclerState()
}