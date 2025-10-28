package com.example.splitsmart20

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitsmart20.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Sign up button
        binding.signupButton.setOnClickListener {
            registerUser()
        }

        // Switch to Login
        binding.loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Social sign ups (placeholders)
        binding.btnAppleSignUp.setOnClickListener {
            Toast.makeText(this, "Apple sign up - to be implemented", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoogleSignUp.setOnClickListener {
            Toast.makeText(this, "Google sign up - to be implemented", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser() {
        val name = binding.nameInput.text.toString().trim()
        val email = binding.signupEmail.text.toString().trim()
        val password = binding.signupPassword.text.toString()
        val confirmPassword = binding.confirmPassword.text.toString()

        if (validateForm(name, email, password, confirmPassword)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registration success
                        showToast("Account created successfully!")
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        // Registration failed
                        showToast("Registration failed: ${task.exception?.message}")
                    }
                }
        }
    }

    private fun validateForm(name: String, email: String, password: String, confirmPassword: String): Boolean {
        // Clear previous errors
        binding.nameInput.error = null
        binding.signupEmail.error = null
        binding.signupPassword.error = null
        binding.confirmPassword.error = null

        return when {
            TextUtils.isEmpty(name) -> {
                binding.nameInput.error = "Enter your full name"
                false
            }
            TextUtils.isEmpty(email) -> {
                binding.signupEmail.error = "Enter email address"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.signupEmail.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password) -> {
                binding.signupPassword.error = "Enter password"
                false
            }
            password.length < 6 -> {
                binding.signupPassword.error = "Password must be at least 6 characters"
                false
            }
            password != confirmPassword -> {
                binding.confirmPassword.error = "Passwords do not match"
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}