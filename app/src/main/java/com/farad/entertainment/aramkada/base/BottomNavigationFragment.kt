package com.farad.entertainment.aramkada.base

import androidx.viewbinding.ViewBinding
import com.farad.entertainment.aramkada.ui.activity.MainActivity


abstract class BottomNavigationFragment<VB : ViewBinding> : BaseFragment<VB>(),
    OnBackPressed {

    fun getMainActivity() = (activity as? MainActivity)
    fun restartDestination() = getMainActivity()?.navigationManager?.restartDestination(false)
    override fun onBackPressedCompact() {
        popBackStack()
    }
}