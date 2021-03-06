package com.example.pmacademyandroid_troschij_metelov_project2.loginScreenFeature

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.clientId
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.hostAuth
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.redirectUrl
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.schema
import com.example.pmacademyandroid_troschij_metelov_project2.Constants.Companion.scopes
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.LoginScreenFragmentBinding


class LoginScreenFragment : Fragment() {

    private lateinit var binding: LoginScreenFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            startGitHubLogin()
        }
    }

    private fun startGitHubLogin() {
        val authIntent = Intent(Intent.ACTION_VIEW, buildAuthGitHubUrl())
        startActivity(authIntent)
    }

    private fun buildAuthGitHubUrl(): Uri {
        return Uri.Builder()
            .scheme(schema)
            .authority(hostAuth)
            .appendEncodedPath("login/oauth/authorize")
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("scope", scopes)
            .appendQueryParameter("redirect_url", redirectUrl)
            .build()
    }

    companion object {
        fun newInstance() = LoginScreenFragment()
    }
}