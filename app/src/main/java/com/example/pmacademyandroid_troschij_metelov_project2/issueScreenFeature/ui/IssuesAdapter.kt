package com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueReposResponse

class IssuesAdapter(
    private var callback: ((IssueReposResponse) -> Unit) = { }
) : PagingDataAdapter<IssueReposResponse, IssuesViewHolder>(SearchComparator) {

    object SearchComparator : DiffUtil.ItemCallback<IssueReposResponse>() {
        override fun areItemsTheSame(oldItem: IssueReposResponse, newItem: IssueReposResponse) =
            oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: IssueReposResponse, newItem: IssueReposResponse) =
            oldItem.number == newItem.number
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.issues_item, parent, false)
        return IssuesViewHolder(view, callback)
    }

}