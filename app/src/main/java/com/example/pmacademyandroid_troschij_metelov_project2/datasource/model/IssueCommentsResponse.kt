package com.example.pmacademyandroid_troschij_metelov_project2.datasource.model

import com.google.gson.annotations.SerializedName

class IssueCommentsResponse(
    @SerializedName("user") val user: UserResponse,
    @SerializedName("body") val body: String,
    @SerializedName("created_at") val created_at: String
)