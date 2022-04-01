package com.jovian.test1ev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.jovian.test1ev.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityHomeBinding.inflate(layoutInflater).also { binding = it }
        .root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navHosFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHosFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(
        //Top-level destinations
        R.id.loginFragment,
        R.id.logoutFragment

    ),
    drawerLayout
    )

    NavigationUI.setupWithNavController(navView, navController)
    NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

}
}