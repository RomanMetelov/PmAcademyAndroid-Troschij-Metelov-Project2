package com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.data.repository.impl

import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.dataSource.GitHubUsersRemoteDataSource
import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.data.GitHubUsersModel
import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.data.GitHubUsersResponseToModelMapper
import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.data.repository.GitHubUsersRepository

class GitHubUsersRepositoryImpl(
    private val remoteDataSource: GitHubUsersRemoteDataSource,
    private val gitHubUsersResponseToModelMapper: GitHubUsersResponseToModelMapper
) : GitHubUsersRepository {

    override suspend fun getUsers(usernameQuery: String): List<GitHubUsersModel>? {
        return gitHubUsersResponseToModelMapper.map(remoteDataSource.getGitHubUsers(usernameQuery))
    }
}