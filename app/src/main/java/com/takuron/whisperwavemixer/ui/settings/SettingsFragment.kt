package com.takuron.whisperwavemixer.ui.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.takuron.whisperwavemixer.R


class SettingsFragment : PreferenceFragmentCompat() {

    @SuppressLint("ResourceAsColor")
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        view?.setBackgroundColor(R.color.md_theme_background)

        findPreference<Preference>("code")
            ?.setOnPreferenceClickListener {
                val url = requireContext().getText(R.string.settings_about_source_url)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
                activity?.startActivity(intent)
                true
            }
    }
}