package com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pmacademyandroid_troschij_metelov_project2.utils.api.GitHubService
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfileRepository
import com.example.pmacademyandroid_troschij_metelov_project2.utils.BaseViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.utils.PagingDataSource
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ClientAppState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectScreenViewModel @Inject constructor(
    private val profileRepository: UserProfileRepository,
    private val gitHubService: GitHubService
) : BaseViewModel() {
    private val _readMeLiveData = MutableLiveData<ClientAppState<String, Int>>()
    val readMeLiveData: LiveData<ClientAppState<String, Int>> = _readMeLiveData

    private val _contributorsLiveData =
        MutableLiveData<ClientAppState<PagingData<UserResponse>, String>>()
    val contributorsLiveData: LiveData<ClientAppState<PagingData<UserResponse>, String>> =
        _contributorsLiveData

    private val _issuesLiveData =
        MutableLiveData<ClientAppState<PagingData<IssueReposResponse>, Exception>>()
    val issuesLiveData: LiveData<ClientAppState<PagingData<IssueReposResponse>, Exception>> =
        _issuesLiveData

    val scopeProjectScreen = baseScope

    fun getContributors(repoName: String, owner: String) {
        baseScope.launch {
            _contributorsLiveData.postValue(ClientAppState.Loading)
            reposFlow(
                repoName,
                owner
            ).collectLatest { pagedData ->
                _contributorsLiveData.postValue(ClientAppState.Data(pagedData))
            }
        }
    }

    private suspend fun reposFlow(owner: String, repoName: String): Flow<PagingData<UserResponse>> {
        return Pager(PagingConfig(40)) {
            PagingDataSource(baseScope) { currentPage ->
                gitHubService.getContributors(40, currentPage, repoName, owner)
            }
        }.flow.cachedIn(baseScope)
    }

    fun getReadMe(owner: String, repoName: String) {
        baseScope.launch {
            _readMeLiveData.postValue(ClientAppState.Loading)
            val text = profileRepository.getReadme(repoName, owner).readMe
            val t = Base64.decode(text, 0).decodeToString()
            _readMeLiveData.postValue(ClientAppState.Data(t))
        }
    }

}