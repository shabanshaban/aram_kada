package com.farad.entertainment.aramkada.base.navigation.base

import android.os.Bundle
import androidx.navigation.NavDirections
import com.farad.entertainment.aramkada.base.BaseActivity
import com.farad.entertainment.aramkada.data.maneger.navigate.NavigationManager


interface BaseNavigator {

    val baseActivity: BaseActivity<*>

    fun navigate(
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.NONE
    )


    fun navigate(
        navDirections: NavDirections,
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.DEFAULT
    )

    fun navigate(
        id: Int,
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.DEFAULT
    )

    fun navigate(
        id: Int,
        bundle: Bundle,
        pageAnimation: NavigationManager.PageAnimation = NavigationManager.PageAnimation.DEFAULT
    )
}

