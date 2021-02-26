package com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreen.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.RepoViewHolderBinding
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.ReposResponse


class ReposViewHolder(view: View, private var callback: ((ReposResponse) -> Unit) = { }) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: RepoViewHolderBinding

    fun onBind(reposResponse: ReposResponse, ){
        binding = RepoViewHolderBinding.bind(itemView)
        binding.apply {
            tvReposName.setOnClickListener { callback(reposResponse) }
            tvReposName.text = reposResponse.name
        }
    }
}

