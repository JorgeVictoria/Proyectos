package com.jovian.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.jovian.mvvm.databinding.ActivityMainBinding
import com.jovian.mvvm.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    //para montar el binding
    private lateinit var binding: ActivityMainBinding

    //variable para contactar el viewModel
    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //para cargar el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        //desde aqui observamos los cambios en el viewmodel
        quoteViewModel.quoteModel.observe(this, Observer { currentQuote ->
            binding.quote.text = currentQuote.quote
            binding.author.text = currentQuote.author
        })

        quoteViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        //desde aqui llamamos a la funcion del viewmodel
        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }
    }
}