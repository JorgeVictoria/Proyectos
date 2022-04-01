package com.jovian.activities.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.jovian.activities.R

class Activity2 : AppCompatActivity() {

    private val etName : EditText by lazy { findViewById(R.id.etName) }
    private val btnBack : Button by lazy {findViewById(R.id.btnBack)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        btnBack.setOnClickListener{openMainActivity()}
    }

    private fun openMainActivity() {
        val text: String = etName.text.toString()

        /**val intent = Intent(this, MainActivity::class.java)

        if(text.isNotEmpty()) {
        val bundle = Bundle()
        bundle.putString("name", text)
        intent.putExtras(bundle)
        }

        startActivity(intent)

        }**/

        //para no aumentar la pila de activities, devolvemos con setResult
        val intent = Intent()
        intent.putExtra("name", text)

        if(text.length == 0) setResult(RESULT_CANCELED, intent)
        else setResult(RESULT_OK, intent)

        finish()
    }
}