package com.example.pmacademyandroid_troschij_metelov_project2.utils.api.interceptors

import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.DATA_LOADING_MESSAGE
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.NOT_FOUND_MESSAGE
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.UNAUTHORIZED_MESSAGE
import com.example.pmacademyandroid_troschij_metelov_project2.utils.api.Exceptions
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            401 -> throw Exceptions.Unauthorized(UNAUTHORIZED_MESSAGE)
            404 -> throw Exceptions.NotFound(NOT_FOUND_MESSAGE)
            in 400..500 -> throw Exceptions.DataLoading(DATA_LOADING_MESSAGE)
        }

        val bodyLine = response.body?.string()
        return response.newBuilder()
            .body(bodyLine?.toResponseBody(response.body?.contentType()))
            .build()
    }
}