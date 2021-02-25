package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.repository

import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.GitHubUsersModel

interface GitHubUsersRepository {
    suspend fun getUsers(usernameQuery: String): List<GitHubUsersModel>?
}