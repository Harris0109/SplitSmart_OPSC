package com.example.splitsmart20

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitsmart20.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        setupClickListeners()

        // Check if user is already logged in
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is already logged in, redirect to home
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setupClickListeners() {
        // Login button
        binding.btnLogin.setOnClickListener {
            signInUser()
        }

        // Switch to Sign Up
        binding.signupTitle.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        // Forgot password
        binding.forgotPassword.setOnClickListener {
            // Simple forgot password implementation
            val email = binding.etEmail.text.toString().trim()
            if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Failed to send reset email", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                binding.etEmail.error = "Enter a valid email to reset password"
            }
        }

        // Social logins (placeholders)
        binding.btnAppleLogin.setOnClickListener {
            Toast.makeText(this, "Apple login - to be implemented", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoogleLogin.setOnClickListener {
            Toast.makeText(this, "Google login - to be implemented", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (validateForm(email, password)) {
            // Show loading (you can add a progress dialog here later)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login success
                        showToast("Login successful!")
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        // Login failed
                        showToast("Login failed: ${task.exception?.message}")
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        // Clear previous errors
        binding.etEmail.error = null
        binding.etPassword.error = null

        return when {
            TextUtils.isEmpty(email) -> {
                binding.etEmail.error = "Enter email address"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.etEmail.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password) -> {
                binding.etPassword.error = "Enter password"
                false
            }
            password.length < 6 -> {
                binding.etPassword.error = "Password must be at least 6 characters"
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}