package com.jovian.dogsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jovian.dogsearch.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //creates an instance of Retrofit
    private fun getRetrofit ():Retrofit {
        return Retrofit.Builder ()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
   //this function recieves a String with the Breed name
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds(("$query/images"))
            val puppies = call.body()
            if (call.isSuccessful) {
                //show RecyclerView
            } else {
                //show error
            }
        }
    }
}