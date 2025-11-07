package com.example.splitsmart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat multiLanguageToggle;
    private Spinner languageSpinner;
    private SwitchCompat notificationsToggle;
    private SwitchCompat offlineSyncToggle;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPreferences";
    private static final String KEY_MULTI_LANGUAGE = "multi_language";
    private static final String KEY_SELECTED_LANGUAGE = "selected_language";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_OFFLINE_SYNC = "offline_sync";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize UI components
        multiLanguageToggle = findViewById(R.id.multi_language_toggle);
        languageSpinner = findViewById(R.id.language_spinner);
        notificationsToggle = findViewById(R.id.notifications_toggle);
        offlineSyncToggle = findViewById(R.id.offline_sync_toggle);

        // Set up the language spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Load saved preferences
        loadPreferences();

        // Set up listeners
        multiLanguageToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            languageSpinner.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            sharedPreferences.edit().putBoolean(KEY_MULTI_LANGUAGE, isChecked).apply();
        });

        notificationsToggle.setOnCheckedChangeListener((buttonView, isChecked) ->
                sharedPreferences.edit().putBoolean(KEY_NOTIFICATIONS, isChecked).apply());

        offlineSyncToggle.setOnCheckedChangeListener((buttonView, isChecked) ->
                sharedPreferences.edit().putBoolean(KEY_OFFLINE_SYNC, isChecked).apply());

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = parent.getItemAtPosition(position).toString();
                sharedPreferences.edit().putString(KEY_SELECTED_LANGUAGE, selectedLanguage).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void loadPreferences() {
        boolean isMultiLanguage = sharedPreferences.getBoolean(KEY_MULTI_LANGUAGE, false);
        multiLanguageToggle.setChecked(isMultiLanguage);
        languageSpinner.setVisibility(isMultiLanguage ? View.VISIBLE : View.GONE);

        String selectedLanguage = sharedPreferences.getString(KEY_SELECTED_LANGUAGE, "English");
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) languageSpinner.getAdapter();
        int position = adapter.getPosition(selectedLanguage);
        languageSpinner.setSelection(position);

        boolean isNotificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS, true);
        notificationsToggle.setChecked(isNotificationsEnabled);

        boolean isOfflineSyncEnabled = sharedPreferences.getBoolean(KEY_OFFLINE_SYNC, false);
        offlineSyncToggle.setChecked(isOfflineSyncEnabled);
    }
}
