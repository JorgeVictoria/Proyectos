package com.jovian.p5shoppinglist.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovian.p5shoppinglist.R
import com.jovian.p5shoppinglist.database.entities.Producto

//esta clase sirve para rellenar cada uno de los elementos de la lista
class ListaCompraAdapter(

    //variables de la clase
    //lista de productos del carrito
    val productos: List<Producto>,
    //Para cheqquear la lista cada vez que eliminamos la cantidad de productos
    val checkProducto: (Producto) -> Unit) : RecyclerView.Adapter<ListaCompraAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.itemproducto, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productos[position]
        holder.bind(item, checkProducto)
    }

    override fun getItemCount() = productos.size



    //capturamos en variables los elementos de cada etiqueta y les damos un valor
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNombreProducto = view.findViewById<TextView>(R.id.tvProducto)
        val tvPrecioUnidad = view.findViewById<TextView>(R.id.tvPrecioUnidad)
        val tvCantidadProducto = view.findViewById<TextView>(R.id.tvTotalProducto)
        val tvPrecioTotalProducto = view.findViewById<TextView>(R.id.tvPrecioTotal)
        val btnDecrementarCantidadProducto = view.findViewById<ImageButton>(R.id.btnRestar)

        fun bind(producto: Producto, checkProducto: (Producto) -> Unit) {
            tvNombreProducto.text = producto.nombreProducto
            tvPrecioUnidad.text = String.format("%.2f €",producto.precioUnidad)
            tvCantidadProducto.text = producto.cantidad.toString()
            tvPrecioTotalProducto.text = String.format("%.2f €",producto.precioTotal)
            btnDecrementarCantidadProducto.setOnClickListener{checkProducto(producto)} }
        }

}