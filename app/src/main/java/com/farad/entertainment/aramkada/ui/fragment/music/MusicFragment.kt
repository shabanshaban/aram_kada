package com.farad.entertainment.aramkada.ui.fragment.music

import android.view.LayoutInflater
import android.view.ViewGroup
import com.farad.entertainment.aramkada.base.BottomNavigationFragment
import com.farad.entertainment.aramkada.databinding.FragmentMusicBinding

class MusicFragment : BottomNavigationFragment<FragmentMusicBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMusicBinding
        get() = FragmentMusicBinding::inflate

    override fun setup() {

    }
}