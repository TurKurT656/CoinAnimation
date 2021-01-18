package com.example.coinanimation.ktx

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce.DAMPING_RATIO_HIGH_BOUNCY
import androidx.dynamicanimation.animation.SpringForce.STIFFNESS_MEDIUM

fun View.springAnimate(
    startScaleX: Float = 1.2f,
    startScaleY: Float = 1.2f,
    dampingRatio: Float = DAMPING_RATIO_HIGH_BOUNCY,
    stiffness: Float = STIFFNESS_MEDIUM
) {
    scaleX = startScaleX
    scaleY = startScaleY
    SpringAnimation(this, DynamicAnimation.SCALE_X, 1f)
        .apply {
            spring.dampingRatio = dampingRatio
            spring.stiffness = stiffness
        }
        .start()
    SpringAnimation(this, DynamicAnimation.SCALE_Y, 1f)
        .apply {
            spring.dampingRatio = dampingRatio
            spring.stiffness = stiffness
        }
        .start()
}