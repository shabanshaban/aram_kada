package com.farad.entertainment.aramkada.ui.fragment.video

import android.view.LayoutInflater
import android.view.ViewGroup
import com.farad.entertainment.aramkada.base.BottomNavigationFragment
import com.farad.entertainment.aramkada.databinding.FragmentVideoBinding

class VideoFragment : BottomNavigationFragment<FragmentVideoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentVideoBinding
        get() = FragmentVideoBinding::inflate

    override fun setup() {

    }
}