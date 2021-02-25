package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.UserViewHolderBinding
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.UserResponse

class UserViewHolder(view: View, private val onClickListener: View.OnClickListener?) :
    RecyclerView.ViewHolder(view) {
    private lateinit var binding: UserViewHolderBinding

    fun onBind(userResponse: UserResponse) {
        binding = UserViewHolderBinding.bind(itemView)
        binding.ugUser.apply {
            setOnClickListener(onClickListener)
            setName(userResponse.name)
            setImage(userResponse.avatar_url)
        }
    }
}

