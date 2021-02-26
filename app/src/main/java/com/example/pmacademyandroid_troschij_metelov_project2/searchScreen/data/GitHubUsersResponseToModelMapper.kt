package com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data

import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.GitHubUserResponse

class GitHubUsersResponseToModelMapper {
    fun map(responses: List<GitHubUserResponse>?): List<GitHubUsersModel>? {
        return responses?.let {
            responses.map {
                GitHubUsersModel(
                    it.id,
                    it.login,
                    it.reposUrl,
                    it.avatarUrl,
                )
            }
        }
    }
}