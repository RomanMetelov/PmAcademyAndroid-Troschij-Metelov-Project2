package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder

import androidx.recyclerview.widget.DiffUtil
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.ReposResponse

class ReposDiffCallBack : DiffUtil.ItemCallback<ReposResponse>() {
    override fun areItemsTheSame(oldItem: ReposResponse, newItem: ReposResponse): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ReposResponse, newItem: ReposResponse): Boolean {
        return oldItem.name == newItem.name
    }
}