package com.jovian.p5shoppinglist.database

import com.jovian.p5shoppinglist.database.entities.Producto

interface MyDao {

    //mostramos todos los productos del carrito
    fun getAllProductos(): MutableList<Producto>

    //calculo del importe total de la compra
    fun getTotal(): Double

    //añadir un producto a la lista. Recibe un producto. Devuelve un long
    fun addProducto(producto: Producto):Long //id del nuevo producto

    //buscar un registro(producto de la lista) por su id. Recibe una id y devuelve un producto
    fun getProductoId(id: Long): Producto

    //actualizar los datos del producto, como su precio o la cantidad que queda
    fun updateProducto(producto: Producto):Int  //numero de lineas afectadas?

    //borrar un producto de la lista cuando su cantidad es 0
    fun borrarProducto(producto: Producto):Int  //numero de lineas afectadas?
}