package com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.dataSource

import retrofit2.Retrofit

class GitHubUsersRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getGitHubUsers(usernameQuery: String) =
        retrofit.create(GitHubUsersApi::class.java).getUsersByQuery(usernameQuery, 999).items
}