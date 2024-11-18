package com.farad.entertainment.aramkada.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

fun View.visibleOrInvisible(show: Boolean, animate: Boolean = false) {
    if (show) visible(animate) else invisible(animate)
}

fun View.invisible(animate: Boolean = false) {
    hide(View.INVISIBLE, animate)
}

fun View.gone(animate: Boolean = false) {
    hide(View.GONE, animate)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = false) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}

fun View.visibleOrGone(show: Boolean, animate: Boolean = false) {
    if (show) visible(animate) else gone(animate)
}

fun View.visible(animate: Boolean = false) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

fun View.slideDown() {
    val animate = TranslateAnimation(0f, 0f, 0f, height.toFloat())
    animate.duration = 300
    animate.fillAfter = false
    startAnimation(animate)
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            gone(false)
        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })

}

fun View.slideUp() {

    visible(false)
    val animate = TranslateAnimation(0f, 0f, height.toFloat(), 0f)
    animate.duration = 300
    startAnimation(animate)
}