package com.example.electrorui.ui

import android.os.Bundle
import android.view.Window
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.electrorui.R
import com.example.electrorui.databinding.ActivityMainBinding
import com.example.electrorui.db.PrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        prefManager = PrefManager(this)

        prefManager.setTipoRescate(0)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navView.setOnApplyWindowInsetsListener(null)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_dashboard, R.id.navigation_notifications
        ).build()
//        val appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    fun navigateToNavBarDestination(destinationId: Int) {
        binding.navView.setSelectedItemId(destinationId)
    }
}