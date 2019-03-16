package com.wunder9l.firsthomework

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wunder9l.firsthomework.util.toText
import kotlinx.android.synthetic.main.activity_second.*
import java.util.concurrent.TimeUnit

class SecondActivity : AppCompatActivity() {
    val timer = object : CountDownTimer(TimeUnit.SECONDS.toMillis(1000), TimeUnit.SECONDS.toMillis(1)) {
        override fun onFinish() {
            start()
        }

        override fun onTick(value: Long) {
            counter++
            updateState()
        }
    }

    override fun onStop() {
        Log.d("onStop", "stop $this")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("onDestroy", "destroy $this")
        timer.cancel()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (counter == 0) {
            restoreState()
        } else {
            updateState()
            if (isCountDown) {
                button.text = "stop"
                button.setOnClickListener { stopButtonPressed() }
                 timer.start()
            } else {
//                timer.cancel()
                button.text = "start"
                button.setOnClickListener { startButtonPressed() }
            }
        }
    }

    fun restoreState() {
        textView.text = ""
        button.text = "start"
        button.setOnClickListener { startButtonPressed() }
        timer.cancel()
        counter = 0
        isCountDown = false
    }

    private fun startButtonPressed() {
//        counter = if (counter == 0) 1 else counter
        updateState()
        button.text = "stop"
        button.setOnClickListener { stopButtonPressed() }
        timer.start()
        isCountDown = true
    }

    private fun stopButtonPressed() {
        timer.cancel()
        isCountDown = false
        button.text = "start"
        button.setOnClickListener { startButtonPressed() }
    }

    private fun updateState() {
        if (counter == 10) {
            restoreState()
            textView.text = "тысяча"
        } else {
            textView.text = toText(counter)
            Log.d("updateState", "$counter - ${textView.text}")
        }
    }

    companion object {
        var counter: Int = 0
        var isCountDown: Boolean = false
    }

}
