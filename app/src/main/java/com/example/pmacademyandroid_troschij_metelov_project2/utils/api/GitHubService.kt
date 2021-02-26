package com.example.pmacademyandroid_troschij_metelov_project2.utils.api

import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueCommentsResponse
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueDetailsResponse
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.loginScreenFeature.AccessTokenResponse
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.ReadMeResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.ReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse
import retrofit2.http.*

interface GitHubService {

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
    ): AccessTokenResponse

    @POST("/repos/{owner}/{repo}/issues/comments/{comment_id}/reactions")
    suspend fun createReactionForIssueComment(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("comment_id") comment_id: Int,
        @Body reaction: String
    )

    @GET("/user")
    suspend fun getUserByToken(): UserResponse

    @GET("/users/{user}")
    suspend fun getUserByNickname(@Path("user") user: String): UserResponse

    @GET("/user/repos")
    suspend fun getReposByToken(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): List<ReposResponse>

    @GET("/users/{owner}/repos")
    suspend fun getReposByNickname(
        @Path("owner") owner: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
    ): List<ReposResponse>

    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun getContributors(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Path("repo") repo: String,
        @Path("owner") owner: String
    ): List<UserResponse>

    @Headers("Accept: application/vnd.github.squirrel-girl-preview")
    @GET("/repos/{owner}/{repo}/issues")
    suspend fun getIssues(
        @Path("repo") repo: String,
        @Path("owner") owner: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): List<IssueReposResponse>

    @GET("/repos/{owner}/{repo}/issues/{issue_number}")
    suspend fun getIssueDetail(
        @Path("repo") repo: String,
        @Path("owner") owner: String,
        @Path("issue_number") issue_number: Int
    ): IssueDetailsResponse

    @GET("/repos/{owner}/{repo}/issues/{issue_number}/comments")
    suspend fun getIssueComments(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Path("repo") repo: String,
        @Path("owner") owner: String,
        @Path("issue_number") issue_number: Int
    ): List<IssueCommentsResponse>

    @GET("/repos/{owner}/{repo}/readme")
    suspend fun getReadme(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): ReadMeResponse
}