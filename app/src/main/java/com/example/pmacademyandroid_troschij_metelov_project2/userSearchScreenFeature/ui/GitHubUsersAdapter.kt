package com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.SearchItemBinding
import com.example.pmacademyandroid_troschij_metelov_project2.userSearchScreenFeature.ui.GitHubUsersUIModel

class GitHubUsersAdapter(private val callback: (GitHubUsersUIModel) -> Unit) :
    ListAdapter<GitHubUsersUIModel, SearchViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.callback = callback
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitHubUsersUIModel>() {
            override fun areItemsTheSame(
                oldItem: GitHubUsersUIModel,
                newItem: GitHubUsersUIModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: GitHubUsersUIModel,
                newItem: GitHubUsersUIModel
            ): Boolean =
                oldItem == newItem
        }
    }
}

class SearchViewHolder(
    private val binding: SearchItemBinding,
    var callback: ((GitHubUsersUIModel) -> Unit) = {}
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(gitHubUser: GitHubUsersUIModel) {
        binding.tvGitHubUsername.text = gitHubUser.login
        loadImageUrl(binding.ivAvatar, gitHubUser.avatarUrl)
        binding.tvGitHubUsername.setOnClickListener {
            callback(gitHubUser)
        }
    }

    private fun loadImageUrl(view: AppCompatImageView, imageUrl: String) {
        Glide.with(view)
            .load(imageUrl)
            .circleCrop()
            .into(view)
    }
}