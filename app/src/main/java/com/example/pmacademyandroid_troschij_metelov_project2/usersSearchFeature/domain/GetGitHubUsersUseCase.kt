package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.domain

import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.repository.GitHubUsersRepository
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.ui.GitHubUsersUIModel


class GetGitHubUsersUseCase (
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val gitHubUsersModelToUIMapper: GitHubUsersModelToUIMapper
) {

    suspend fun execute(usernameQuery: String): List<GitHubUsersUIModel>? {
        return gitHubUsersModelToUIMapper.map(gitHubUsersRepository.getUsers(usernameQuery))
    }
}