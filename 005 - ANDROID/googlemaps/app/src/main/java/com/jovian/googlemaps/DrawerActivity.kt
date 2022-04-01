package com.jovian.googlemaps

import android.os.Bundle
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.jovian.googlemaps.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarDrawer.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val mainOptions = listOf(R.id.option1, R.id.option4)
        val menu = navView.menu
        val secOptions = listOf(R.id.option2, R.id.option3, R.id.option5, R.id.option6)

        for(option in mainOptions){
            val tools = menu.findItem(option)

            tools.icon.setTint(getColor(R.color.black))

            val s = SpannableString(tools.title)

            s.setSpan(TextAppearanceSpan(this, R.style.MainOptionStyle),0, s.length, 0)

            tools.title = s
        }

        for(option in secOptions){
            val tools = menu.findItem(option)

            tools.icon.setTint(getColor(R.color.black))

            val s = SpannableString(tools.title)

            s.setSpan(TextAppearanceSpan(this, R.style.SecondaryOptionStyle),0, s.length, 0)

            tools.title = s
        }


        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "${item.itemId}", Toast.LENGTH_SHORT).show()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}