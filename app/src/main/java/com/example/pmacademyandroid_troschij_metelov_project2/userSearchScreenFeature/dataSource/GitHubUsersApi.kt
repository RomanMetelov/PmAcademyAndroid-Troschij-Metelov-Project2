package com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.dataSource

import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.GitHubUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubUsersApi {

    @GET("search/users")
    suspend fun getUsersByQuery(
        @Query("q") q: String,
        @Query("per_page") per_page: Int
    ): GitHubUsersResponse
}