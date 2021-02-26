package com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.domain

import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.ui.GitHubUsersUIModel
import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.data.repository.GitHubUsersRepository

class GetGitHubUsersUseCase (
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val gitHubUsersModelToUIMapper: GitHubUsersModelToUIMapper
) {

    suspend fun execute(usernameQuery: String): List<GitHubUsersUIModel>? {
        return gitHubUsersModelToUIMapper.map(gitHubUsersRepository.getUsers(usernameQuery))
    }
}