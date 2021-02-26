package com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.PAGE_SIZE
import com.example.pmacademyandroid_troschij_metelov_project2.utils.api.GitHubService
import com.example.pmacademyandroid_troschij_metelov_project2.issueScreenFeature.IssueReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.utils.BaseViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.utils.PagingDataSource
import com.example.pmacademyandroid_troschij_metelov_project2.utils.ClientAppState
import com.example.pmacademyandroid_troschij_metelov_project2.projectScreenFeature.UserProject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class IssuesScreenViewModel @Inject constructor(
    private val gitHubService: GitHubService
) : BaseViewModel() {

    private val _issuesLiveData =
        MutableLiveData<ClientAppState<PagingData<IssueReposResponse>, Exception>>()
    val issuesLiveData
        get() = _issuesLiveData as LiveData<ClientAppState<PagingData<IssueReposResponse>, Exception>>

    val viewModelScope = this.baseScope

    fun getIssues(userProject: UserProject) {
        baseScope.launch {
            reposFlow(
                userProject.userName,
                userProject.repoName
            ).collectLatest { pagedData ->
                _issuesLiveData.postValue(ClientAppState.Data(pagedData))
            }
        }
    }

    private suspend fun reposFlow(
        owner: String,
        repoName: String
    ): Flow<PagingData<IssueReposResponse>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            PagingDataSource(baseScope) { currentPage ->
                gitHubService.getIssues(owner, repoName, PAGE_SIZE, currentPage)
            }
        }.flow.cachedIn(baseScope)
    }
}