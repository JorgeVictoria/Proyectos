package com.jovian.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val rvLista: RecyclerView by lazy { findViewById(R.id.rvRecycler) }

    val personas = listOf(
        Persona("Jorge", "Victoria","123456789"),
        Persona("Jordi", "Victoria", "987654321"),
        Persona("Jorge", "Andreu","123456789"),
        Persona("Jordi", "Andreu", "987654321")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()

    }

    fun initRecycler(){

        rvLista.layoutManager = LinearLayoutManager(this)
        val adapter = PersonAdapter(personas)
        rvLista.adapter = adapter
    }
}