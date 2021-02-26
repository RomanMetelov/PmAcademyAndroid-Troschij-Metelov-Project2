package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.contributorsScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ContributorViewHolderBinding
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse

class ContributorViewHolder(view: View, private var callback: ((UserResponse) -> Unit) = { }) :
    RecyclerView.ViewHolder(view) {
    private lateinit var binding: ContributorViewHolderBinding

    fun onBind(userResponse: UserResponse) {
        binding = ContributorViewHolderBinding.bind(itemView)
        binding.apply {
            tvUser.setOnClickListener { callback(userResponse) }
            tvUser.setName(userResponse.name)
            tvUser.setImage(userResponse.name)
        }
    }
}
