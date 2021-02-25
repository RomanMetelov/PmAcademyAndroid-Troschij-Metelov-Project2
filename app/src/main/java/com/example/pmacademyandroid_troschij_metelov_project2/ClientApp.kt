package com.example.pmacademyandroid_troschij_metelov_project2

import android.app.Application
import com.example.pmacademyandroid_troschij_metelov_project2.di.AppComponent
import com.example.pmacademyandroid_troschij_metelov_project2.di.AppModule
import com.example.pmacademyandroid_troschij_metelov_project2.di.DaggerAppComponent

class ClientApp : Application() {
    lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getComponent(): AppComponent {
        return daggerComponent
    }
}