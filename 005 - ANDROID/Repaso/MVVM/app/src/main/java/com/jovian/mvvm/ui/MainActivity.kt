package com.jovian.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jovian.mvvm.databinding.ActivityMainBinding
import com.jovian.mvvm.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    //creamos el binding
    private lateinit var binding: ActivityMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //creamos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.quoteModel.observe(this, Observer {  currentQuote ->
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })

        binding.viewContainer.setOnClickListener{quoteViewModel.randomQuote()}
    }
}