package com.example.splitsmart20

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitsmart20.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        setupUserInfo()
        setupClickListeners()
    }

    private fun setupUserInfo() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            binding.tvUserEmail.text = "Logged in as: ${currentUser.email}"
        } else {
            binding.tvUserEmail.text = "No user logged in"
        }
    }

    private fun setupClickListeners() {
        // Logout button
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            showToast("Logged out successfully")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Continue button (placeholder for now)
        binding.btnContinue.setOnClickListener {
            showToast("Continue to main app - to be implemented")
            // You can replace this with your main app activity later
            // startActivity(Intent(this, MainAppActivity::class.java))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}