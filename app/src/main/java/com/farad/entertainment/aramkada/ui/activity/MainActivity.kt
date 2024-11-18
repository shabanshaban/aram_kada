package com.farad.entertainment.aramkada.ui.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatDelegate
import com.farad.entertainment.aramkada.R
import com.farad.entertainment.aramkada.base.BaseActivity
import com.farad.entertainment.aramkada.base.OnBackPressed
import com.farad.entertainment.aramkada.base.navigation.main.MainNavigator
import com.farad.entertainment.aramkada.data.maneger.navigate.NavigationManager
import com.farad.entertainment.aramkada.databinding.ActivityMainBinding
import com.farad.entertainment.aramkada.utils.gone
import com.farad.entertainment.aramkada.utils.visible
import com.farad.entertainment.aramkada.utils.visibleOrGone

class MainActivity : BaseActivity<ActivityMainBinding>(), MainNavigator {

    override fun getBindingView() = ActivityMainBinding.inflate(layoutInflater)

    override fun onBackPressedCompact() {
        val fragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull()
        val stackSize = navigationManager?.getBackStackEntryCount() ?: return
        if (stackSize == 0) {
            super.onBackPressedCompact()
        } else if (fragment is OnBackPressed) fragment.onBackPressedCompact()
        else navigationManager?.popBackStack()
    }

    fun showBottomNavigation(isShown: Boolean) {
        binding.bottomNavigation.visibleOrGone(isShown, true)

    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun afterCreateView() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setupNavigationManager()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO



    }

    override fun onDestroy() {
        navigationManager?.release()
        navigationManager = null
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        setupNavigationManager()
    }


    private fun setupNavigationManager() {
        navHostFragment = getNavHostFragment(R.id.nav_host_container)!!
        NavigationManager.setupWithNavController(
            binding.bottomNavigation,
            navHostFragment?.navController,
            false
        )
        navigationManager =
            NavigationManager(navHostFragment!!, R.id.main_nav_graph).apply {

                onDestinationChangedListener = { destination, _ ->
                    when (destination.id) {
                        R.id.homeFragment,
                        R.id.videoFragment,
                        R.id.musicFragment,
                        R.id.breathingFragment,
                        -> {
                            binding.materialCardViewToolbar.visible()

                        }

                        else -> {
                            binding.materialCardViewToolbar.gone()
                        }
                    }

                }

            }

        navigationManager?.start()
    }

}