package com.example.pmacademyandroid_troschij_metelov_project2.datasource.model

import com.google.gson.annotations.SerializedName

data class IssueDetailsResponse (
    @SerializedName("number") val number: Int,
    @SerializedName("user") val user: UserResponse,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("created_at") val created_at: String
)
