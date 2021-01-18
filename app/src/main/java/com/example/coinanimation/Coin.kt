package com.example.coinanimation

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageView

class Coin(context: Context) : AppCompatImageView(context) {

    init {
        setImageResource(R.drawable.ic_coin)
    }

    companion object {
        fun addTo(
            frameLayout: FrameLayout,
            layoutParams: FrameLayout.LayoutParams =
                FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                    .apply {
                        gravity = Gravity.CENTER
                    }
        ): Coin {
            val coin = Coin(frameLayout.context)
            coin.layoutParams = layoutParams
            frameLayout.addView(coin)
            return coin
        }
    }

}