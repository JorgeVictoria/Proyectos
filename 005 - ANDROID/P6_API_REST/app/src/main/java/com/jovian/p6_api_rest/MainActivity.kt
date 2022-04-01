package com.jovian.p6_api_rest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovian.p6_api_rest.adapters.AmiiboAdapter
import com.jovian.p6_api_rest.databinding.ActivityMainBinding
import com.jovian.p6_api_rest.interfaces.APIService
import com.jovian.p6_api_rest.model.Amiibo
import com.jovian.p6_api_rest.model.AmiiboResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var binding: ActivityMainBinding

    private lateinit var adapter: AmiiboAdapter
    private val amiiboImages = mutableListOf<Amiibo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svAmiibos.setOnQueryTextListener(this)

        initRecyvlerView()
    }

    private fun initRecyvlerView() {
        adapter = AmiiboAdapter(amiiboImages)
        binding.rvAmiibos.layoutManager = LinearLayoutManager(this)
        binding.rvAmiibos.adapter = adapter
    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl("https://dog.ceo/api/breed/")
            .baseUrl("https://www.amiiboapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {

        CoroutineScope(Dispatchers.IO).launch {
            Log.e("aviso", "estoy aqui")
            val call = getRetrofit().create(APIService::class.java).getAmiiboByName("amiibo/?character=$query")
            val amiibos: AmiiboResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful) {

                    //Show recyclerview
                    val images: ArrayList<Amiibo> = (amiibos?.amiibo ?: emptyList()) as ArrayList<Amiibo>

                    amiiboImages.clear()
                    amiiboImages.addAll(images)
                    adapter.notifyDataSetChanged()


                } else {
                    //show error
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Character Not Found", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}


