package com.mudrokhub.mudrokhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Handler().postDelayed({
            // Start the next activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Close the splash activity
            finish()
        }, 3000.toLong())
    }
}