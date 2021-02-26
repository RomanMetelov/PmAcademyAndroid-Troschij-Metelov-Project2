package com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature

import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse
import com.google.gson.annotations.SerializedName

class IssueReposResponse (
    @SerializedName("number") val number: Int,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: UserResponse,
    @SerializedName("comments") val comments: Int
)
