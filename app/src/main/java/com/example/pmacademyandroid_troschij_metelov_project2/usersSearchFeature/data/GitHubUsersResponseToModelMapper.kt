package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data

import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.datasource.GitHubUserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.domain.orZero


class GitHubUsersResponseToModelMapper {
    fun map(responses: List<GitHubUserResponse>?): List<GitHubUsersModel>? {
        return responses?.let {
            responses.map {
                GitHubUsersModel(
                    it.id.orZero(),
                    it.login,
                    it.reposUrl,
                    it.avatarUrl,
                )
            }
        }
    }
}