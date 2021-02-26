package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.contributorsScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse

class ContributorsAdapter(private var callback: ((UserResponse) -> Unit) = {}) :
    PagingDataAdapter<UserResponse, ContributorViewHolder>(ContributorsDiffCallBack()) {

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contributor_view_holder, parent, false)
        return ContributorViewHolder(view, callback)
    }
}