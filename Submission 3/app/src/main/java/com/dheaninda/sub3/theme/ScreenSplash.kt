package com.dheaninda.part1.theme

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dheaninda.part1.R
import com.dheaninda.part1.ui.MainActivity

class ScreenSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@ScreenSplash, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}