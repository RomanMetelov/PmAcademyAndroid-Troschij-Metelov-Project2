package com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.ReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.adapter.holder.ReposDiffCallBack
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.adapter.holder.ReposViewHolder

class ReposAdapter(private var callback: ((ReposResponse) -> Unit) = { }) :
    PagingDataAdapter<ReposResponse, ReposViewHolder>(ReposDiffCallBack()) {

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_view_holder, parent, false)
        return ReposViewHolder(view, callback)
    }
}