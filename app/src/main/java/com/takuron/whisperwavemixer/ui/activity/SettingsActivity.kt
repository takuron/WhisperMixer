package com.takuron.whisperwavemixer.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.takuron.whisperwavemixer.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.topToolBar.setNavigationOnClickListener {
            finish()
        }

    }
}