package com.jovian.navgitationwithmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.jovian.navgitationwithmenu.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityOptionsBinding.inflate(layoutInflater).also { binding = it }.root)

        setSupportActionBar(binding.toolbar)

        val navHosFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHosFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.options1Fragment,
                R.id.options2Fragment,
            )
        )

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}