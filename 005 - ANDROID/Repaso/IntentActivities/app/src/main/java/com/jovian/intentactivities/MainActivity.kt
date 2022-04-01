package com.jovian.intentactivities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private val tvGreeting: TextView by lazy { findViewById(R.id.tvMessage) }
    private val btnGo: Button by lazy { findViewById(R.id.btnGo) }

    var myResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            //When data go back we put it in the text view
            tvGreeting.text = "${data?.getStringExtra(Intent.EXTRA_RETURN_RESULT)}"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGo.setOnClickListener { sendShared() }

    }

    private fun sendShared() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Hi there! pass that to uppercase and send back.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(//Selector that allow us choose destination app according the action and intent-filter
            sendIntent,
            "Hey choose an app to uppercase") //Title of selector window
        myResultLauncher.launch(shareIntent)
    }
}






