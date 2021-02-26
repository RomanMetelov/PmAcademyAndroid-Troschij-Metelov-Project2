package com.example.pmacademyandroid_troschij_metelov_project2.loginScreen


import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.clientId
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.clientSecret
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.GitHubService
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.AccessTokenResponse
import com.example.pmacademyandroid_troschij_metelov_project2.tools.sharedPref.SharedPref
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val gitHubService: GitHubService,
    private val sharedPref: SharedPref
) {
    suspend fun refreshToken(code: String){
        val response = getAccessToken(code)
        val token = "${response.tokenType} ${response.accessToken}"
        sharedPref.token = token
    }
    private suspend fun getAccessToken(code: String): AccessTokenResponse {
        return gitHubService.getAccessToken(clientId, clientSecret, code)
    }
}