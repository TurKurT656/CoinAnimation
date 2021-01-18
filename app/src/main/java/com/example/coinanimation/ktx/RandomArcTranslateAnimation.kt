package com.example.coinanimation.ktx

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
import kotlin.random.Random

inline fun View.randomArcAnimation(
    fromX: Float,
    toX: Float,
    fromY: Float,
    toY: Float,
    translateDurationRange: Pair<Long, Long> = 800L to 1_200L,
    rotationDurationRange: Pair<Long, Long> = 200L to 500L,
    crossinline onAnimationEnd: () -> Unit,
) {
    val translateDuration =
        Random.nextLong(translateDurationRange.first, translateDurationRange.second)
    AnimatorSet().apply {
        playTogether(
            ObjectAnimator
                .ofFloat(this@randomArcAnimation, "rotation", 0f, 360f)
                .also {
                    it.duration = Random.nextLong(rotationDurationRange.first, rotationDurationRange.second)
                    it.repeatMode = ValueAnimator.RESTART
                    it.repeatCount = ValueAnimator.INFINITE
                    it.interpolator = LinearInterpolator()
                },
            ObjectAnimator
                .ofFloat(this@randomArcAnimation, "translationX", fromX, toX)
                .also {
                    it.duration = translateDuration
                    it.interpolator = AccelerateInterpolator(Random.nextDouble(.5, 1.5).toFloat())
                },
            ObjectAnimator
                .ofFloat(this@randomArcAnimation, "translationY", fromY, toY)
                .also {
                    it.duration = translateDuration
                    it.interpolator = LinearInterpolator()
                }
        )
    }
        .apply {
            addListener(
                onEnd = {
                    onAnimationEnd()
                }
            )
        }
        .start()
}