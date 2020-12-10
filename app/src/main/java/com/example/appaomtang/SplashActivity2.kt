package com.example.appaomtang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle

class SplashActivity2 : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val runnable = Runnable {
        if (!isFinishing){
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}