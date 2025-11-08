package com.example.splitsmart20

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class SettingsActivity : AppCompatActivity() {

    private lateinit var multiLanguageToggle: SwitchCompat
    private lateinit var languageSpinner: Spinner
    private lateinit var notificationsToggle: SwitchCompat
    private lateinit var offlineSyncToggle: SwitchCompat

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var home : Button
    private lateinit var group : Button
    private lateinit var expense : Button
    private val PREFS_NAME = "AppPreferences"
    private val KEY_MULTI_LANGUAGE = "multi_language"
    private val KEY_SELECTED_LANGUAGE = "selected_language"
    private val KEY_NOTIFICATIONS = "notifications"
    private val KEY_OFFLINE_SYNC = "offline_sync"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        home = findViewById(R.id.homeBtn)
        group = findViewById(R.id.groupBtn)
        expense = findViewById(R.id.expenseBtn)

        home.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
        group.setOnClickListener {
            val intent = Intent(this, AddGroupActivity::class.java)
            startActivity(intent)
        }
        expense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Initialize UI components
        multiLanguageToggle = findViewById(R.id.multi_language_toggle)
        languageSpinner = findViewById(R.id.language_spinner)
        notificationsToggle = findViewById(R.id.notifications_toggle)
        offlineSyncToggle = findViewById(R.id.offline_sync_toggle)

        // Set up the language spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.language_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = adapter

        // Load saved preferences
        loadPreferences()

        // Set up listeners
        multiLanguageToggle.setOnCheckedChangeListener { _, isChecked ->
            languageSpinner.visibility = if (isChecked) View.VISIBLE else View.GONE
            sharedPreferences.edit().putBoolean(KEY_MULTI_LANGUAGE, isChecked).apply()
        }

        notificationsToggle.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(KEY_NOTIFICATIONS, isChecked).apply()
        }

        offlineSyncToggle.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(KEY_OFFLINE_SYNC, isChecked).apply()
        }

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedLanguage = parent.getItemAtPosition(position).toString()
                sharedPreferences.edit().putString(KEY_SELECTED_LANGUAGE, selectedLanguage).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun loadPreferences() {
        val isMultiLanguage = sharedPreferences.getBoolean(KEY_MULTI_LANGUAGE, false)
        multiLanguageToggle.isChecked = isMultiLanguage
        languageSpinner.visibility = if (isMultiLanguage) View.VISIBLE else View.GONE

        val selectedLanguage = sharedPreferences.getString(KEY_SELECTED_LANGUAGE, "English") ?: "English"
        val adapter = languageSpinner.adapter as ArrayAdapter<CharSequence>
        val position = adapter.getPosition(selectedLanguage)
        languageSpinner.setSelection(position)

        val isNotificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS, true)
        notificationsToggle.isChecked = isNotificationsEnabled

        val isOfflineSyncEnabled = sharedPreferences.getBoolean(KEY_OFFLINE_SYNC, false)
        offlineSyncToggle.isChecked = isOfflineSyncEnabled
    }
}
