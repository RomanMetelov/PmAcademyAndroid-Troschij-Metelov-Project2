package com.example.pmacademyandroid_troschij_metelov_project2.tools.navigator

import androidx.fragment.app.Fragment
import com.example.pmacademyandroid_troschij_metelov_project2.navigationActivity.NavigationActivity

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected val navigation: Navigator by lazy {
        (requireActivity() as NavigationActivity).navigator
    }
}