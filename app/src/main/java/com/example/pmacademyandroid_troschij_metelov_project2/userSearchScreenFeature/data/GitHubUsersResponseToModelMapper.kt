package com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.data

import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.GitHubUserResponse

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