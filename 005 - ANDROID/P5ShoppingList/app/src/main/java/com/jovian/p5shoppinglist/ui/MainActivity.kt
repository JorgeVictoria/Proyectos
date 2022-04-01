package com.jovian.p5shoppinglist.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jovian.p5shoppinglist.adapters.ListaCompraAdapter
import com.jovian.p5shoppinglist.database.entities.Producto
import com.jovian.p5shoppinglist.databinding.ActivityMainBinding
import com.jovian.p5shoppinglist.viewmodel.ProductoViewModel

/**
 * clase principal de la aplicacion
 * en este caso usamos una activity compuesta de una cabecera donde ingresamos los datos
 * una lista(recyclerView) donde iran apareciendo los distintos productos que debemos comprar
 * y finalmente un bloque donde aparece el importe total de la compra
 */
class MainActivity : AppCompatActivity() {

    //variables locales de la activity
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var producto: MutableList<Producto> = mutableListOf()
    private lateinit var productoViewModel: ProductoViewModel
    lateinit var adapter: ListaCompraAdapter

    //en el oncreate de la activity, además de cargar los datos
    //creamos los observer de los distintos elementos de la app
    override fun onCreate(savedInstanceState: Bundle?) {

        //inflate con los elementos del lalyout
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also {
            binding = it
        }.root)

        //variable para coger el viewmodel
        productoViewModel = ViewModelProvider(this)[ProductoViewModel::class.java]

        //cogemos todos los elementos de la bbdd?
        productoViewModel.getAllProductos()

        //observer para controlar el precio de la compra
        //aplicamos un string format para mostrar 2 decimales
        productoViewModel.totalCompraLD.observe(this) { totalCompra->
        binding.tvPrecioTotal.text = String.format("%.2f €", totalCompra)
        }

        //observer de la lista de los productos añadidos
        productoViewModel.productoListLD.observe(this){
            producto.clear()
            producto.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        }

        //observer para controlar los cambios de cada elemento de la lista
        //Solo controlamos el decremento de los productos de la lista y si
        //su contador queda a cero se eliminan de la lista
        productoViewModel.updateProductoLD.observe(this){ productoUpdated ->
            if(productoUpdated == null){
                showMessage("Error updating producto")
            }
        }

        //Observer para el borrado de productos
        //Realmente no lo utilizo porque el borrado lo hago de otra forma
        //TODO trastear tras la entrega de la practica
        productoViewModel.deleteProductoLD.observe(this){ id ->
            if(id != -1){
                val task = producto.filter {
                    it.id == id
                }[0]
                val pos = producto.indexOf(task)
                producto.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }else{
                showMessage("Error deleting task")
            }
        }

        //observer para controlar la insercion de nuevos productos en la lista
        productoViewModel.insertProductoLD.observe(this){
            producto.add(it)
            recyclerView.adapter?.notifyItemInserted(producto.size)


        }

        //funcion para añadir un producto a la lista tras pulsar el boton +
        binding.btnAAdir.setOnClickListener {
            añadirProducto()
        }

        setUpRecyclerView()
    }

    /**
     * funcion para mostrar un mensaje de error?
     * TODO trastear tras la entrega de la practica
     */
    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }


    /**
     * funcion para añadir un producto
     * se pasa al viewmodel todos los valores que capturamos en el layout, como el nombre del producto
     * el precio de la unidad o la cantida de producto que compramos
     */
    private fun añadirProducto() {
        productoViewModel.add(binding.etProducto.text.toString(),binding.etPrecioUnidad.text.toString(),binding.etCantidad.text.toString() )
        clearFocus()
        hideKeyboard()

    }

    /**
     * funcion para armar el recyclerview, haciendo uso del adapter
     */
    fun setUpRecyclerView() {
        adapter = ListaCompraAdapter(producto, { producto ->  updateProducto(producto) })
        recyclerView = binding.rvListaProductos
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    //funcion para actualizar el producto de la lista
    private fun updateProducto(producto: Producto) {
        productoViewModel.update(producto)
    }

    //funcion para borrar un producto
    //TODO borrar tras entrega
    private fun deleteTask(producto: Producto) {
        productoViewModel.delete(producto)

    }

    /**
     * con esta funcion dejamos en blanco los editText donde ingresamos la informacion
     * cada vez que insertamos un nuevo producto a la lista
     */
    private fun clearFocus(){
        binding.etProducto.setText("")
        binding.etCantidad.setText("")
        binding.etPrecioUnidad.setText("")
    }

    /**
     * funcion para ocultar el teclado del movil tras realizar una insercion en la lista
     */
    @SuppressLint("ServiceCast")
    private fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}