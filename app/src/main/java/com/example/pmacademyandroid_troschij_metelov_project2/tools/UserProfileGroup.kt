package com.example.pmacademyandroid_troschij_metelov_project2

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.UserProfileGroupBinding

class UserGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding =
        UserProfileGroupBinding.bind(inflate(context, R.layout.user_profile_group, this))

    fun setName(name: String) {
        binding.tvUserName.text = name
    }

    fun getName(): String {
        return binding.tvUserName.text.toString()
    }

    fun setImage(imageUrl: String) {
        Glide.with(this).load(imageUrl).circleCrop().into(binding.ivUserAvatar)
    }
}
