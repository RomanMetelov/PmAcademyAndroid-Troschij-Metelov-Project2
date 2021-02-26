package com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.PAGE_SIZE
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfileRepository
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.ReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ClientAppState
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.utils.BaseViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.utils.PagingDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileScreenViewModel @Inject constructor(
    private val profileRepository: UserProfileRepository,
) : BaseViewModel() {
    lateinit var userProfile: UserProfile
    val scope = baseScope
    private val _userInfoLiveData = MutableLiveData<ClientAppState<UserResponse, Int>>()
    val userInfoLiveData: LiveData<ClientAppState<UserResponse, Int>> = _userInfoLiveData

    private val _reposLiveData = MutableLiveData<PagingData<ReposResponse>>()
    val reposLiveData: LiveData<PagingData<ReposResponse>> = _reposLiveData

    init {
        _userInfoLiveData.value = ClientAppState.Loading
    }

    private val repos = Pager(PagingConfig(PAGE_SIZE)) {
        PagingDataSource(scope) {
            profileRepository.getRepos(userProfile)
        }
    }.flow

    fun getRepos() {
        scope.launch {
            repos.collect { pagedItem ->
                _reposLiveData.postValue(pagedItem)
            }
        }
    }

    fun getUser() {
        viewModelScope.launch(exceptionsHandler) {
            launch {
                _userInfoLiveData.value =
                    ClientAppState.Data(profileRepository.getUser(userProfile))
            }
        }
    }

}
