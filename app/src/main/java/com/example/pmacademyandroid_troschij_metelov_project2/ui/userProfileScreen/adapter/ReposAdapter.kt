package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.ReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder.ReposDiffCallBack
import com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder.ReposViewHolder

class ReposAdapter(private val onClickListener: View.OnClickListener? = null) :
    PagingDataAdapter<ReposResponse, ReposViewHolder>(ReposDiffCallBack()) {

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_view_holder, parent, false)
        return ReposViewHolder(view, onClickListener)
    }
}