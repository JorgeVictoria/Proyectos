package com.jovian.parcelables.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.jovian.parcelables.R
import com.jovian.parcelables.model.Person

class MainActivity2 : AppCompatActivity() {
    private val etName : EditText by lazy { findViewById(R.id.etName) }
    private val etSurname: EditText by lazy { findViewById(R.id.etSurname) }
    private val btnBack : Button by lazy {findViewById(R.id.btnBack)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnBack.setOnClickListener{openMainActivity()}
    }

    private fun openMainActivity() {
        val name: String = etName.text.toString()
        val surname: String = etSurname.text.toString()
        val persona = Person(name, surname)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("ObjetoParcelable", persona)

        startActivity(intent)

    }
}
