package com.example.pmacademyandroid_troschij_metelov_project2.di

import android.content.Context
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.host
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.schema
import com.example.pmacademyandroid_troschij_metelov_project2.repository.UserProfileRepository
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.interceptors.ErrorInterceptor
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.GitHubService
import com.example.pmacademyandroid_troschij_metelov_project2.datasource.api.interceptors.HeaderInterceptor
import com.example.pmacademyandroid_troschij_metelov_project2.tools.sharedPref.SharedPref
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideGitHubReposApi(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(gitHubService: GitHubService): UserProfileRepository {
        return UserProfileRepository(gitHubService)
    }

    @Provides
    @Singleton
    fun provideSharedPref(context: Context): SharedPref {
        return SharedPref(context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, sharedPref: SharedPref): Retrofit {
        return Retrofit.Builder().client(
            OkHttpClient().newBuilder()
                .addInterceptor(ErrorInterceptor())
                .addInterceptor(HeaderInterceptor(sharedPref))
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        ).baseUrl(HttpUrl.Builder().scheme(schema).host(host).build())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }

    @Singleton
    @Provides
    fun context(): Context {
        return context
    }
}