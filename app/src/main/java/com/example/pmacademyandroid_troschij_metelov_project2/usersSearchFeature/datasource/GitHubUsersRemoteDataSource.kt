package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.datasource

import retrofit2.Retrofit

class GitHubUsersRemoteDataSource(private val retrofit: Retrofit) {
    suspend fun getGitHubUsers(usernameQuery: String) = //эта функция делает запрос на сервак (и ожидает получить массив а получает объект?)
        retrofit.create(GitHubUsersApi::class.java).getUsersByQuery(usernameQuery, 999).items
}