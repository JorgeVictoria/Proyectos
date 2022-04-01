package com.jovian.p5shoppinglist.database

import androidx.room.*
import com.jovian.p5shoppinglist.database.entities.Producto

@Dao
interface ProductoDao:MyDao {

    //select de todos los productos de la lista
    @Query("SELECT * FROM productos")
    override fun getAllProductos(): MutableList<Producto>

    //select para calcular el importe total de la compra
    @Query("SELECT SUM(precioTotal) from productos")
    override fun getTotal(): Double

    //añadimos un producto
    @Insert
    override fun addProducto(producto : Producto):Long

    //select para coger un registro por su id
    @Query("SELECT * FROM productos WHERE id LIKE :id")
    override fun getProductoId(id: Long): Producto

    //actualizacion de los registros
    @Update
    override fun updateProducto(producto: Producto):Int

    //borrado de los registros
    @Delete
    override fun borrarProducto(producto: Producto):Int
}