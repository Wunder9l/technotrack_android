package com.wunder9l.firsthomework

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val timer = object : CountDownTimer(TimeUnit.SECONDS.toMillis(2), TimeUnit.SECONDS.toMillis(1)) {
        override fun onFinish() {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
        

        override fun onTick(value: Long) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timer.start()
    }
}
