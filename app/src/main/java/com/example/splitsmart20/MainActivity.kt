package com.example.splitsmart20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the button and make it work
        val getStartedButton = findViewById<Button>(R.id.btnGetStarted)

        getStartedButton.setOnClickListener {
            // When button is clicked, go to Login Screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}