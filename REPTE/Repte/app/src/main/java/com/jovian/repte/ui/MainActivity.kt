package com.jovian.repte.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jovian.repte.Adapter.ProductoAdapter
import com.jovian.repte.R
import com.jovian.repte.databinding.ActivityMainBinding
import com.jovian.repte.interfaces.ApiService
import com.jovian.repte.model.Producto
import com.jovian.repte.model.PedidoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    //variable para preparar el binding
    lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductoAdapter
    private val productos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding para los elementos del layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //cogemos el texto escrito en la busqueda
        binding.svProductos.setOnQueryTextListener(this)

        initRecyvlerView()
    }

    /**
     * funcion para crear el recycler view
     */
    private fun initRecyvlerView() {
        adapter = ProductoAdapter(productos)
        binding.rvProductos.layoutManager = LinearLayoutManager(this)
        binding.rvProductos.adapter = adapter
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
            .baseUrl("http://172.26.80.37:8069/almacem/apirest/obtenerPedidos/") //la url debe acabar siempre con barra
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
            val call = getRetrofit().create(ApiService::class.java).getPedidoById("$query")
            val productosResponse: PedidoResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful) {

                    //Show recyclerview
                    val misProductos: ArrayList<Producto> = (productosResponse?.pedidos ?: emptyList()) as ArrayList<Producto>

                    productos.clear()
                    productos.addAll(productos)
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