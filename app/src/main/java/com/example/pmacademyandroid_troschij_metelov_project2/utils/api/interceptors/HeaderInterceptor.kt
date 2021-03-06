package com.example.pmacademyandroid_troschij_metelov_project2.utils.api.interceptors

import com.example.pmacademyandroid_troschij_metelov_project2.utils.sharedPref.SharedPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val sharedPref: SharedPref
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Accept", "application/vnd.github.v3+json")
                .addHeader("Authorization", sharedPref.token)
                .build()
        )
    }
}