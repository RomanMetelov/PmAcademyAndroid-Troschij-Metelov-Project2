package com.example.pmacademyandroid_troschij_metelov_project2.ui.userProfileScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pmacademyandroid_troschij_metelov_project2.repository.UserProfileRepository
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.ReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.tools.State
import com.example.pmacademyandroid_troschij_metelov_project2.tools.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.tools.BaseViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.tools.PagingDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileScreenViewModel @Inject constructor(
    private val profileRepository: UserProfileRepository,
) : BaseViewModel() {
    lateinit var userProfile: UserProfile
    val scope = baseViewModelScope
    private val _userInfoLiveData = MutableLiveData<State<UserResponse, Int>>()
    val userInfoLiveData: LiveData<State<UserResponse, Int>> = _userInfoLiveData

    private val _reposLiveData = MutableLiveData<PagingData<ReposResponse>>()
    val reposLiveData: LiveData<PagingData<ReposResponse>> = _reposLiveData

    init {
        _userInfoLiveData.value = State.Loading
    }

    private val repos = Pager(PagingConfig(20)) {
        PagingDataSource(scope) {
            profileRepository.getRepos(UserProfile.UserCurrent)
        }
    }.flow

    fun getRepos() {
        scope.launch {
            repos.collect { pagedItem ->
                _reposLiveData.postValue(pagedItem)
            }
        }
    }
    fun getContent() {
        viewModelScope.launch(exceptionHandler) {
            launch {
                _userInfoLiveData.value = State.Data(profileRepository.getUser(userProfile))
            }
        }
    }

}
