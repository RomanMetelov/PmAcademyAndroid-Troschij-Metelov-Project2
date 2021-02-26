package com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.repository

import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.GitHubUsersModel

interface GitHubUsersRepository {
    suspend fun getUsers(usernameQuery: String): List<GitHubUsersModel>?
}