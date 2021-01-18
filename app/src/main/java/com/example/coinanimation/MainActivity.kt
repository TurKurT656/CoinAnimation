package com.example.coinanimation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.coinanimation.ktx.randomArcAnimation
import com.example.coinanimation.ktx.springAnimate
import com.example.coinanimation.ktx.toPx
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity(), Runnable {

    private val edgeHeight by lazy { (parentView.height - 2 * toolbar.height - 24.toPx) / 2f }
    private val edgeWidth by lazy { (parentView.width + 24.toPx) / 2f }
    private val handler = Handler(Looper.getMainLooper())

    private var coinMaxCount = 0
    private var coinCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        addCoinButton.setOnClickListener {
            it?.springAnimate(stiffness = 500f)
            coinMaxCount = Random.nextInt(1, 50)
            coinCount = 0
            handler.removeCallbacks(this)
            handler.post(this)
        }
    }

    override fun run() {
        if (coinCount < coinMaxCount) {
            val coin = Coin.addTo(parentView)
            coin.randomArcAnimation(0f, -edgeWidth, 0f, -edgeHeight) {
                parentView.removeView(coin)
            }
            handler.postDelayed(this, 50)
            coinCount++
        }
    }

    override fun onPause() {
        handler.removeCallbacks(this)
        super.onPause()
    }

}