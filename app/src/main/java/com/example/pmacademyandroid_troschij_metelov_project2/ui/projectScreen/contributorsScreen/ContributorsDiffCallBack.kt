package com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.contributorsScreen

import androidx.recyclerview.widget.DiffUtil
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.UserResponse

class ContributorsDiffCallBack : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.name == newItem.name
    }
}