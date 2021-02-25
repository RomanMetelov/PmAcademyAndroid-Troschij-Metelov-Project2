package com.example.pmacademyandroid_troschij_metelov_project2.repository

import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.GitHubService
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.*
import com.example.pmacademyandroid_troschij_metelov_project2.tools.UserProfile
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
    private val gitHubService: GitHubService,
) {
    suspend fun getUser(userProfile: UserProfile): UserResponse {
        return when (userProfile) {
            is UserProfile.UserCurrent -> gitHubService.getUserByToken()
            is UserProfile.UserPublic -> gitHubService.getUserByNickname(userProfile.userName)
        }
    }

    suspend fun getRepos(userProfile: UserProfile): List<ReposResponse> {
        return when (userProfile) {
            is UserProfile.UserCurrent -> gitHubService.getReposByToken(30, 0)
            is UserProfile.UserPublic -> gitHubService.getReposByNickname(userProfile.userName, 30, 0
            )
        }
    }

    suspend fun getContributorsForProjectDetail(repo: String, owner: String): List<UserResponse> {
        return gitHubService.getContributors(30, 0, repo, owner)
    }

    suspend fun getIssuesForProjectDetail(repo: String, owner: String): List<IssueReposResponse> {
        return gitHubService.getIssues(30, 0, repo, owner)
    }

    suspend fun getIssueDetails(repo : String, owner : String, issueNumber : Int): IssueDetailsResponse{
        return gitHubService.getIssueDetail(repo,owner,issueNumber)
    }

    suspend fun getIssueComments(repo : String, owner : String, issueNumber : Int) : List<IssueCommentsResponse>{
        return gitHubService.getIssueComments(30,0,repo,owner,issueNumber)
    }

    suspend fun getReadme(repo : String, owner : String) : String {
        return gitHubService.getReadme(owner, repo)
    }

}