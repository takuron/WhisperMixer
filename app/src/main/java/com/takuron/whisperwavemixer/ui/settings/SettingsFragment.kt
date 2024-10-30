package com.takuron.whisperwavemixer.ui.settings

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceFragmentCompat
import com.takuron.whisperwavemixer.R

class SettingsFragment : PreferenceFragmentCompat() {
    val viewModel:SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}