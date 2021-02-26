package com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.domain

import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.ui.GitHubUsersUIModel
import com.example.pmacademyandroid_troschij_metelov_project2.searchScreen.data.GitHubUsersModel

class GitHubUsersModelToUIMapper {

    fun map(models: List<GitHubUsersModel>?): List<GitHubUsersUIModel>? {
        return models?.let {
            models.map {
                GitHubUsersUIModel(
                    it.id,
                    it.login,
                    it.reposUrl,
                    it.avatarUrl,
                    R.drawable.ic_launcher_foreground
                )
            }
        }
    }
}