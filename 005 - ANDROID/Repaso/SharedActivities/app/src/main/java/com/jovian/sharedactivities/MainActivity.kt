package com.jovian.sharedactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val tvShared: TextView by lazy { findViewById(R.id.tvShared) }
    private val btnBack: Button by lazy { findViewById(R.id.btnSendBack) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = intent
        var text:String? = null

        data?.let{
            if(data.hasExtra(Intent.EXTRA_TEXT))
                text = data.getStringExtra(Intent.EXTRA_TEXT).toString().uppercase()

            tvShared.text = text
        }

        btnBack.setOnClickListener {
            val intent = Intent().apply {
                putExtra(Intent.EXTRA_RETURN_RESULT, text?:"No text")
            }

            setResult(RESULT_OK,intent)
            finish()
        }


    }
}

