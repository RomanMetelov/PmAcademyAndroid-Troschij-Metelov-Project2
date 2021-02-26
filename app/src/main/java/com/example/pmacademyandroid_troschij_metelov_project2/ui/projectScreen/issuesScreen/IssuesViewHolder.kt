package com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen.issuesScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.IssuesItemBinding
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.IssueReposResponse

class IssuesViewHolder(
    view: View, private var callback: (IssueReposResponse) -> Unit = { }
) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: IssuesItemBinding

    fun onBind(issueResponse: IssueReposResponse) {
        binding = IssuesItemBinding.bind(itemView)
        binding.apply {
            root.setOnClickListener {
                callback(issueResponse)
            }
            tvItem.text = issueResponse.title
        }
    }
}