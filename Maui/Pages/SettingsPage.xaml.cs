using System;
using Microsoft.Maui.Controls;
using Microsoft.Maui.Storage;
using Microsoft.Maui.Graphics;

namespace SplitSmart.Maui.Pages
{
    public partial class SettingsPage : ContentPage
    {
        const string KEY_MULTI_LANG = "pref_multi_lang_enabled";
        const string KEY_LANGUAGE = "pref_selected_language";
        const string KEY_NOTIFICATIONS = "pref_notifications_enabled";
        const string KEY_OFFLINE_SYNC = "pref_offline_sync_enabled";

        public SettingsPage()
        {
            InitializeComponent();

            // Style switches
            MultiLangSwitch.OnColor = Color.FromArgb("#4CAF50");
            NotificationsSwitch.OnColor = Color.FromArgb("#4CAF50");
            OfflineSyncSwitch.OnColor = Color.FromArgb("#4CAF50");

            // Set default nav icon colors
            NavIcon0.TextColor = Color.FromArgb("#666666");
            NavIcon1.TextColor = Color.FromArgb("#666666");
            NavIcon2.TextColor = Color.FromArgb("#666666");
            NavIcon3.TextColor = Color.FromArgb("#666666");

            // Highlight Settings icon as active
            NavIcon4.TextColor = Color.FromArgb("#E53935");
            NavLabel4.TextColor = Color.FromArgb("#E53935");

            LoadPreferences();
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();
            // ensure UI matches persisted values in case preferences changed elsewhere
            LoadPreferences();
        }

        void LoadPreferences()
        {
            try
            {
                bool multi = Preferences.Get(KEY_MULTI_LANG, false);
                MultiLangSwitch.IsToggled = multi;
                LanguagePicker.IsVisible = multi;

                string lang = Preferences.Get(KEY_LANGUAGE, "English");
                // set picker selected index based on saved string
                int idx = 0;
                switch (lang)
                {
                    case "English": idx = 0; break;
                    case "isiXhosa": idx = 1; break;
                    case "Afrikaans": idx = 2; break;
                    default: idx = 0; break;
                }
                LanguagePicker.SelectedIndex = idx;

                bool notif = Preferences.Get(KEY_NOTIFICATIONS, true);
                NotificationsSwitch.IsToggled = notif;

                bool offline = Preferences.Get(KEY_OFFLINE_SYNC, false);
                OfflineSyncSwitch.IsToggled = offline;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error loading preferences: " + ex);
            }
        }

        void OnMultiLangToggled(object sender, ToggledEventArgs e)
        {
            bool isOn = e.Value;
            LanguagePicker.IsVisible = isOn;
            Preferences.Set(KEY_MULTI_LANG, isOn);
        }

        void OnLanguageSelected(object sender, EventArgs e)
        {
            if (LanguagePicker.SelectedIndex < 0) return;
            var selected = LanguagePicker.Items[LanguagePicker.SelectedIndex];
            Preferences.Set(KEY_LANGUAGE, selected);

            // Optionally, apply language change across the app.
            // Implementation depends on app localization strategy and is intentionally left as a hook.
            Console.WriteLine($"Language selected: {selected}");
        }

        void OnNotificationsToggled(object sender, ToggledEventArgs e)
        {
            Preferences.Set(KEY_NOTIFICATIONS, e.Value);
        }

        void OnOfflineSyncToggled(object sender, ToggledEventArgs e)
        {
            Preferences.Set(KEY_OFFLINE_SYNC, e.Value);
        }

        void OnNavTapped(object sender, EventArgs e)
        {
            // The sender for a Tapped event declared on the TapGestureRecognizer is the recognizer itself.
            // Read the CommandParameter from the recognizer to determine which nav item was tapped.
            string param = null;
            if (sender is Microsoft.Maui.Controls.TapGestureRecognizer recognizer)
            {
                param = recognizer.CommandParameter?.ToString();
            }

            // If a parameter wasn't found on the recognizer, we fall back to an "unknown" message.

            // Demo behavior: show which nav index was tapped. Replace this with app-specific navigation logic.
            var message = param == null ? "Navigation tapped (unknown)" : $"Navigation tapped: {param}";
            _ = DisplayAlert("Navigation", message, "OK");
        }
    }
}
