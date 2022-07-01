package com.example.newsapppp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.newsapppp.R
import com.example.newsapppp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_fragment)

        setBottomNavListener()
        setupActionBarWithNavController(findNavController(R.id.nav_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setBottomNavListener() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainFragment -> {
                    navController.navigate(R.id.mainFragment)
                }
                R.id.saveFragment -> {
                    navController.navigate(R.id.saveFragment)
                }

                R.id.searchNewsFragment -> {
                    navController.navigate(R.id.searchFragment)
                }
            }
            true
        }
    }
}