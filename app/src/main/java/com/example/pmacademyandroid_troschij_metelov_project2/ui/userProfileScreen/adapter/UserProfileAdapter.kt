package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.ProfileType
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder.ErrorViewHolder
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder.ReposViewHolder
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder.UserViewHolder


class UserProfileAdapter(
    private val onClickListener: View.OnClickListener? = null
) : ListAdapter<UserProfileRecyclerState, RecyclerView.ViewHolder>(UserProfileDiffCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UserProfileRecyclerState.Error -> ProfileType.ERROR
            is UserProfileRecyclerState.Repos -> ProfileType.REPOS
            is UserProfileRecyclerState.User -> ProfileType.USER
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ProfileType.values()[viewType]) {
            ProfileType.ERROR -> setupErrorViewHolder(parent)
            ProfileType.USER -> setupUserViewHolder(parent)
            ProfileType.REPOS -> setupReposViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ErrorViewHolder -> holder.onBind((getItem(position) as UserProfileRecyclerState.Error).errorText)
            is ReposViewHolder -> holder.onBind((getItem(position) as UserProfileRecyclerState.Repos).repos)
            is UserViewHolder -> holder.onBind((getItem(position) as UserProfileRecyclerState.User).user)
        }
    }


    private fun setupErrorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.error_view_holder, parent, false)
        return ErrorViewHolder(view)
    }

    private fun setupUserViewHolder(parent: ViewGroup): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view_holder, parent, false)
        return UserViewHolder(view, onClickListener)

    }

    private fun setupReposViewHolder(parent: ViewGroup): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_view_holder, parent, false)
        return ReposViewHolder(view,onClickListener)
    }

}
