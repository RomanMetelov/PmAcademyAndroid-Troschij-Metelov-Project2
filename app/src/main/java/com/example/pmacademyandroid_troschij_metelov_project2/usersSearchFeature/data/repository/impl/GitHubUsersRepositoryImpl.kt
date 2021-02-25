package com.example.frominstantsearch.data.repository.impl

import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.GitHubUsersResponseToModelMapper
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.GitHubUsersModel
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.repository.GitHubUsersRepository
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.datasource.GitHubUsersRemoteDataSource

class GitHubUsersRepositoryImpl (
    private val remoteDataSource: GitHubUsersRemoteDataSource,
    private val gitHubUsersResponseToModelMapper: GitHubUsersResponseToModelMapper
) : GitHubUsersRepository {

    override suspend fun getUsers(usernameQuery: String): List<GitHubUsersModel>? {
        return gitHubUsersResponseToModelMapper.map(remoteDataSource.getGitHubUsers(usernameQuery))
    }
}