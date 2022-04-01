package com.jovian.navgitationwithmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.navgitationwithmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Here our app is ready
        //Set before setContentView
        setTheme(R.style.Theme_NavgitationWithMenu)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        setSupportActionBar(binding.toolbar)


        binding.actionGotoDrawerActivity.setOnClickListener {
            startActivity(Intent(this, DrawerActivity::class.java))
        }
        binding.actionGotoBottomActivity.setOnClickListener {
            startActivity(Intent(this, BottomActivity::class.java))
        }
        binding.actionGotoOptionsActivity.setOnClickListener {
            startActivity(Intent(this, OptionsActivity::class.java))
        }
        binding.actionGotoTabbedActivity.setOnClickListener {
            startActivity(Intent(this, TabbedActivity::class.java))
        }
    }
}