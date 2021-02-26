package com.example.pmacademyandroid_troschij_metelov_project2.utils.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.GET_USER_KEY
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.redirectUrl
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.userProfileScreenFeature.UserProfile

class NavigationActivity : AppCompatActivity(R.layout.navigation_activity) {

    val navigator by lazy {
        Navigator(supportFragmentManager, R.id.fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        val userName = intent.getStringExtra(GET_USER_KEY)
        if (userName != null){
            navigator.showUserScreen(UserProfile.UserPublic(userName))
        }else{
            startFragment()
        }
    }

    private fun startFragment(){
        getCodeFromUri(uri = intent.data)?.let {
            navigator.showUpdatingFragment(it)
            return
        }

        navigator.showLoginScreen()
    }

    private fun getCodeFromUri(uri: Uri?): String? {
        uri ?: return null
        if (!uri.toString().startsWith(redirectUrl)) {
            return null
        }
        return uri.getQueryParameter("code")
    }

    companion object{
        fun start(context : Context, item : String){
            val intent = Intent(context, NavigationActivity::class.java)
            intent.putExtra(GET_USER_KEY,item)
            context.startActivity(intent)
        }
    }

}