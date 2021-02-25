package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter

import androidx.recyclerview.widget.DiffUtil

class UserProfileDiffCallBack : DiffUtil.ItemCallback<UserProfileRecyclerState>() {
    override fun areItemsTheSame(
        oldItem: UserProfileRecyclerState,
        newItem: UserProfileRecyclerState
    ): Boolean {
        return when {
            oldItem is UserProfileRecyclerState.User && newItem is UserProfileRecyclerState.User -> oldItem.user.name == newItem.user.name
            oldItem is UserProfileRecyclerState.Repos && newItem is UserProfileRecyclerState.Repos -> oldItem.repos.name == newItem.repos.name
            else -> oldItem == newItem
        }
    }

    override fun areContentsTheSame(
        oldItem: UserProfileRecyclerState,
        newItem: UserProfileRecyclerState
    ): Boolean {
        return oldItem == newItem
    }
}