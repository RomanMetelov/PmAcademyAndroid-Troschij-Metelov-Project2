package com.example.pmacademyandroid_troschij_metelov_project2.navigationActivity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.redirectUrl
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.tools.UserProfile
import com.example.pmacademyandroid_troschij_metelov_project2.tools.navigator.Navigator

class NavigationActivity : AppCompatActivity(R.layout.navigation_activity) {

    val navigator by lazy {
        Navigator(supportFragmentManager, R.id.fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        startFragment()
    }

    private fun startFragment(){
        getCodeFromUri(uri = intent.data)?.let {
            navigator.showUpdatingFragment(it)
            return
        }

        navigator.showUserScreen(UserProfile.UserCurrent)
    }

    private fun getCodeFromUri(uri: Uri?): String? {
        uri ?: return null
        if (!uri.toString().startsWith(redirectUrl)) {
            return null
        }
        return uri.getQueryParameter("code")
    }

}