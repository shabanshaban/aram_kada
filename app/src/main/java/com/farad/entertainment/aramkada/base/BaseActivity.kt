package com.farad.entertainment.aramkada.base

import android.os.Bundle
import android.view.Window
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.farad.entertainment.aramkada.base.navigation.base.BaseNavigator
import com.farad.entertainment.aramkada.data.maneger.navigate.NavigationManager


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), ViewCreatedActivity,
    OnBackPressed, BaseNavigator {
    protected var onItemBackPressedListener: (() -> Unit)? = null
    private var showBannerListener: (() -> Unit)? = null
    fun setOnBackPressedListener(listener: () -> Unit) {
        onItemBackPressedListener = listener
    }

    fun setShowBannerListener(listener: () -> Unit) {
        onItemBackPressedListener = listener
    }

    protected var navHostFragment: NavHostFragment? = null

    lateinit var binding: VB

    var navigationManager: NavigationManager? = null
    fun getNavHostFragment(id: Int): NavHostFragment? {
        return supportFragmentManager.findFragmentById(id) as? NavHostFragment
    }

    abstract fun getBindingView(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        beforeCreateView()


        super.onCreate(savedInstanceState)
        binding = getBindingView()

        setContentView(binding.root)
        supportActionBar?.hide()
        initBackPressListener()
        afterCreateView()


    }


    private fun initBackPressListener() {
        onBackPressedDispatcher.addCallback(this, true) {
            onBackPressedCompact()
        }
    }

    override fun afterCreateView() {
    }

    override fun beforeCreateView() {
    }

    override fun onBackPressedCompact() {
        finish()
    }


    @Suppress("LeakingThis")
    override val baseActivity = this

    override fun onStart() {
        super.onStart()
        navigationManager?.start()
    }

    override fun onStop() {
        navigationManager?.stop()
        super.onStop()
    }
    override fun onDestroy() {

        super.onDestroy()
    }

}


interface ViewCreatedActivity {

    fun afterCreateView()

    fun beforeCreateView()

}

interface OnBackPressed {
    fun onBackPressedCompact()
}