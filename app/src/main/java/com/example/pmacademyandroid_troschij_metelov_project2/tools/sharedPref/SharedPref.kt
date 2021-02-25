package com.example.pmacademyandroid_troschij_metelov_project2.tools.sharedPref

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(context: Context) {

    private companion object {
        const val KEY_TOKEN = "KEY_TOKEN"
    }
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("test", Context.MODE_PRIVATE)
    }
    var token: String by SharedPrefDelegate(sharedPreferences, KEY_TOKEN, "")
}