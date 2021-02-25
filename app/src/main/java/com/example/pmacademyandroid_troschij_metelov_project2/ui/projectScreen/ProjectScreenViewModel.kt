package com.example.pmacademyandroid_troschij_metelov_project2.ui.projectScreen

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.Exceptions
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.IssueReposResponse
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.model.UserResponse
import com.example.pmacademyandroid_troschij_metelov_project2.repository.UserProfileRepository
import com.example.pmacademyandroid_troschij_metelov_project2.tools.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectScreenViewModel @Inject constructor(
    private val profileRepository : UserProfileRepository,

) : ViewModel(){

    private val _readMeLiveData = MutableLiveData<State<String, Int>>()
    val readMeLiveData: LiveData<State<String, Int>> = _readMeLiveData

    private val _contributorsLiveData = MutableLiveData<State<List<UserResponse>,Int>>()
    val contributorsLiveData : LiveData<State<List<UserResponse>,Int>> = _contributorsLiveData

    private val _issuesLiveData = MutableLiveData<State<List<IssueReposResponse>,Int>>()
    val issuesLiveData : LiveData<State<List<IssueReposResponse>,Int>> = _issuesLiveData

    init{
        _readMeLiveData.value = State.Loading
        _contributorsLiveData.value = State.Loading
        _issuesLiveData.value = State.Loading
    }

    private val exceptionHolder = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is Exceptions.NotFound ->
                setErrorToLiveData(R.string.error_not_found)
            is Exceptions.DataLoading ->
                setErrorToLiveData(R.string.error_data_loading)
        }
    }


    private fun setErrorToLiveData(@StringRes textError: Int) {
        if (_readMeLiveData.value is State.Loading) {
            _readMeLiveData.value = State.Error(R.string.error_not_found)
            _issuesLiveData.value = State.Error(R.string.error_not_found)
            _contributorsLiveData.value = State.Error(R.string.error_not_found)
        } else {
            _readMeLiveData.value =
                State.Error(textError)
        }
    }

    fun getContent(repo : String, owner : String){
        viewModelScope.launch(exceptionHolder){
            launch{
                _readMeLiveData.value = State.Data(profileRepository.getReadme(repo, owner))
                _contributorsLiveData.value = State.Data(profileRepository.getContributorsForProjectDetail(repo,owner))
                _issuesLiveData.value = State.Data(profileRepository.getIssuesForProjectDetail(repo,owner))
            }
        }
    }



}