package com.example.wsconnectuser1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, SignIn::class.java))
        finish()
    }
}