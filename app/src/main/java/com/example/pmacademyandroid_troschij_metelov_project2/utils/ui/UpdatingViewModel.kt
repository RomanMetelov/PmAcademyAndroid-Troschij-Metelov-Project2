package com.example.pmacademyandroid_troschij_metelov_project2.utils.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmacademyandroid_troschij_metelov_project2.loginScreenFeature.AuthRepository
import com.example.pmacademyandroid_troschij_metelov_project2.utils.UpdatingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdatingViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _updateStatusLiveData = MutableLiveData<UpdatingState>()
    val updateStatusLiveData: LiveData<UpdatingState> = _updateStatusLiveData

    fun updateToken(code: String) {
        _updateStatusLiveData.postValue(UpdatingState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.refreshToken(code)
            _updateStatusLiveData.postValue(UpdatingState.COMPLETED)
        }
    }
}