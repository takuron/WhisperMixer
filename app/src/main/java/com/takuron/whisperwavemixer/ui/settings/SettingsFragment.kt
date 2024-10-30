package com.takuron.whisperwavemixer.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.takuron.whisperwavemixer.R


class SettingsFragment : PreferenceFragmentCompat() {
    val viewModel:SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        findPreference<Preference>("code")
            ?.setOnPreferenceClickListener {
                val url = requireContext().getText(R.string.settings_about_source_uri)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
                activity?.startActivity(intent)
                true
            }
    }
}