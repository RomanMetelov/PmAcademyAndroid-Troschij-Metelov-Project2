package com.example.pmacademyandroid_troschij_metelov_project2.utils

sealed class ClientAppState<out T, out V> {
    data class Data<T>(val data: T) : ClientAppState<T, Nothing>()
    data class Error<V>(val error: V) : ClientAppState<Nothing, V>()
    object Unauthorized : ClientAppState<Nothing, Nothing>()
    object Loading : ClientAppState<Nothing, Nothing>()
}