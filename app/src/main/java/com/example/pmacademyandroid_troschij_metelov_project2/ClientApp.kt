package com.example.pmacademyandroid_troschij_metelov_project2

import android.app.Application
import com.example.pmacademyandroid_troschij_metelov_project2.app.AppComponent
import com.example.pmacademyandroid_troschij_metelov_project2.app.AppModule
import com.example.pmacademyandroid_troschij_metelov_project2.app.DaggerAppComponent

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