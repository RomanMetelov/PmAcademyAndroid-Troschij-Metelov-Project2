package com.example.pmacademyandroid_troschij_metelov_project2.tools

sealed class State<out T, out V> {
    data class Data<T>(val data: T) : State<T, Nothing>()
    data class Error<V>(val error: V) : State<Nothing, V>()
    object Unauthorized : State<Nothing, Nothing>()
    object Loading : State<Nothing, Nothing>()
}