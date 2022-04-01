package com.jovian.activities.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.jovian.activities.R

class MainActivity : AppCompatActivity() {

    private val btnGo : Button by lazy { findViewById(R.id.btnGo) }
    private val tvGreeting : TextView by lazy { findViewById(R.id.tvGreeting) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGo.setOnClickListener { openActivity2() }

        /**var myText: String? = null

        if (intent.hasExtra("name"))
            myText = intent.getStringExtra("name").toString()

        tvGreeting.text = myText?: "No user"**/
    }

    private fun openActivity2() {

        val intent = Intent(this, Activity2::class.java)
        //startActivity(intent)
        resultLauncher.launch(intent)

    }

    //para no aumentar la pila de activities, hacemos uso de registerForActivityResult
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            tvGreeting.text = "Hello ${data?.getStringExtra("name")?:"No return"}"
        }
    }
}