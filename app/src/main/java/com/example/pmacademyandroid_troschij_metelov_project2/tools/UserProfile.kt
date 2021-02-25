package com.example.pmacademyandroid_troschij_metelov_project2.tools

sealed class UserProfile {
    object UserCurrent : UserProfile()
    data class UserPublic(val userName: String) : UserProfile()
}