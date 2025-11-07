package com.example.splitsmart20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.splitsmart20.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivityDashboardBinding

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using View Binding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Load user info
        setupUserInfo()
    }

    private fun setupUserInfo() {
        val userId = auth.currentUser?.uid

        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val name = document.getString("fullName") ?: "User"
                        val group = document.getString("groupName") ?: ""
                        val amount = document.getDouble("amountOwed") ?: 0.0

                        binding.tvWelcome.text = "Hi, $name"
                        binding.tvAmountOwed.text = "Amount Owed: R${String.format("%.2f", amount)}"
                        binding.tvGroupName.text = if (group.isNotEmpty()) "Group: $group" else "No group assigned"
                    }
                }
                .addOnFailureListener {
                    binding.tvWelcome.text = "Hi, User"
                    binding.tvAmountOwed.text = "Amount Owed: R0.00"
                    binding.tvGroupName.text = "No group assigned"
                }
        }
    }
}
