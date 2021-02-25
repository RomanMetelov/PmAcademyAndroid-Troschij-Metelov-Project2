package com.example.pmacademyandroid_troschij_metelov_project2.tools

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.Exceptions
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {
    protected val exceptionHandler = CoroutineExceptionHandler{
        _, throwable ->
        when(throwable){
            is Exceptions.NotFound -> Log.d("TAG" ,"NotFound")
        }
    }

    protected val baseViewModelScope = CoroutineScope(SupervisorJob() + exceptionHandler)
}