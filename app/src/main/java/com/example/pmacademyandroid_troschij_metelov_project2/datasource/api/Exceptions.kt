package com.example.pmacademyandroid_troschij_metelov_project2.datasource.api

import java.io.IOException

sealed class Exceptions {
    class NotFound(message: String) : IOException(message)
    class DataLoading(message: String) : IOException(message)
    class Unauthorized(message: String) : IOException(message)
}

