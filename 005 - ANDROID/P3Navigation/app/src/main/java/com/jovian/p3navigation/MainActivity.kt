package com.jovian.p3navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.jovian.p3navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_P3Navigation)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }
            .root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navHosFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHosFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(
            //Top-level destinations
            R.id.homeFragment,
            R.id.somosFragment,
            R.id.ofrecemosFragment,
            R.id.contactoFragment,
            ),
            drawerLayout
        )

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);

    }
}