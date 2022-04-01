package com.jovian.p6_api_rest.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovian.p6_api_rest.interfaces.APIService
import com.jovian.p6_api_rest.model.Amiibo
import com.jovian.p6_api_rest.adapters.AmiiboAdapter
import com.jovian.p6_api_rest.model.AmiiboResponse
import com.jovian.p6_api_rest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * clase principal de la app
 * Esta aplicacion sirve para buscar Amiibo's
 * Un Amiibo son unos complementos de Nintendo para sus videoconsolas y juegos
 * y representan diferentes personajes del Universo Nintendo.
 * En esta aplicacion simplemente buscamos el nombre de un personaje
 * Y si existe algun Amiibo lo mostrara en pantalla, junto con su nombre y a que serie pertenece
 */
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    //variable para preparar el binding
    lateinit var binding: ActivityMainBinding

    private lateinit var adapter: AmiiboAdapter
    private val amiiboImages = mutableListOf<Amiibo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding para los elementos del layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //cogemos el texto escrito en la busqueda
        binding.svAmiibos.setOnQueryTextListener(this)

        initRecyvlerView()
    }

    /**
     * funcion para crear el recycler view
     */
    private fun initRecyvlerView() {
        adapter = AmiiboAdapter(amiiboImages)
        binding.rvAmiibos.layoutManager = LinearLayoutManager(this)
        binding.rvAmiibos.adapter = adapter
    }

    /**
     * metodo para ocultar el teclado de la pantalla al iniciar la busqueda
     */
    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    /**
     * instancia del objeto retrofit
     * contiene la parte fija de la url de la api
     * y el conversor de datos JSON
     * @return un retrofit.Builder
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.amiiboapi.com/api/") //la url debe acabar siempre con barra
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * funcion para buscar un personaje amiibo por su nombre
     */
    private fun searchByName(query: String) {

        //llamada a internet
        // coroutine para realizar llamadas asincronas y no usar el hilo principal
        CoroutineScope(Dispatchers.IO).launch {
            //pasamos la parte variable de la URL, que usaremos en la interfaz
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

    /**
     * para mostrar en pantalla un mensaje cuando no el personaje no tenga amiibos
     */
    private fun showError() {
        Toast.makeText(this, "Character Not Found", Toast.LENGTH_SHORT).show()
    }

    //metodos que se implementan al usar setOnQueryTextListener
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


