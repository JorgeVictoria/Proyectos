package com.jovian.repte.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovian.repte.R
import com.jovian.repte.model.Producto

class ProductoAdapter (val productos: List<Producto>): RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)

        return ProductoViewHolder(layoutInflate.inflate(R.layout.item_producto,parent, false))

    }

    override fun getItemCount(): Int = productos.size

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item: Producto = productos[position]

        holder.tv_id.setText(item.idProducto.toString())
        holder.tv_name.setText(item.nombre.toString())
    }

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_id : TextView = itemView.findViewById(R.id.tvIdproduct)
        val tv_name : TextView = itemView.findViewById(R.id.tvNameProduct)
    }

}