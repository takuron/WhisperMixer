package com.takuron.whisperwavemixer

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.takuron.whisperwavemixer.databinding.ActivityMainBinding
import com.takuron.whisperwavemixer.ui.activity.SettingsActivity
import com.takuron.whisperwavemixer.ui.startActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        binding.topToolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.appbar_settings -> { startActivity(SettingsActivity::class.java) }
            }
            return@setOnMenuItemClickListener true
        }
    }
}