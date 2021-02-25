package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.datasource

import com.google.gson.annotations.SerializedName

data class GitHubUsersResponse (
    @SerializedName("message")
    val message : String,
    @SerializedName("total_count") 
    val totalCount : Int,
    @SerializedName("items") 
    val items : List<GitHubUserResponse>
)

data class GitHubUserResponse (
    @SerializedName("id")
    val id : Int,
    @SerializedName("login")
    val login : String,
    @SerializedName("avatar_url")
    val avatarUrl : String,
    @SerializedName("repos_url")
    val reposUrl : String,
)