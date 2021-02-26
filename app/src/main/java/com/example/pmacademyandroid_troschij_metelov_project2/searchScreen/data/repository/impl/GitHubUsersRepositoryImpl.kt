package com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.repository.impl

import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.dataSource.GitHubUsersRemoteDataSource
import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.GitHubUsersModel
import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.GitHubUsersResponseToModelMapper
import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.repository.GitHubUsersRepository

class GitHubUsersRepositoryImpl (
    private val remoteDataSource: GitHubUsersRemoteDataSource,
    private val gitHubUsersResponseToModelMapper: GitHubUsersResponseToModelMapper
) : GitHubUsersRepository {

    override suspend fun getUsers(usernameQuery: String): List<GitHubUsersModel>? {
        return gitHubUsersResponseToModelMapper.map(remoteDataSource.getGitHubUsers(usernameQuery))
    }
}