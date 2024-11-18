package com.farad.entertainment.aramkada.ui.fragment.breathing

import android.view.LayoutInflater
import android.view.ViewGroup
import com.farad.entertainment.aramkada.base.BottomNavigationFragment
import com.farad.entertainment.aramkada.databinding.FragmentBreathingBinding

class BreathingFragment : BottomNavigationFragment<FragmentBreathingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBreathingBinding
        get() = FragmentBreathingBinding::inflate

    override fun setup() {

    }
}