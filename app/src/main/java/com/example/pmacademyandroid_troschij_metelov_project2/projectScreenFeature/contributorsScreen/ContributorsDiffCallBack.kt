package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.contributorsScreen

import androidx.recyclerview.widget.DiffUtil
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse

class ContributorsDiffCallBack : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.name == newItem.name
    }
}