package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ErrorViewHolderBinding


class ErrorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ErrorViewHolderBinding

    fun onBind(errorText: String) {
        binding = ErrorViewHolderBinding.bind(itemView)
        binding.apply {
            tvErrorText.text = errorText
        }
    }
}