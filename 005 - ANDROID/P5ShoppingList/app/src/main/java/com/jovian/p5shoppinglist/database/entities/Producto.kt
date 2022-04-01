package com.jovian.p5shoppinglist.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * data class que esta compuesto por los elementos que forman parte de cada uno de los
 * productos que vamos a comprar
 */

//referenciamos a la tabla que vamos a crear para almacenar los productos de nuestra compra
@Entity(tableName = "productos")

//data classa con las variables miembro de la clase
data class Producto(
    //dejamos que el sistema gestione la primary key de la tabla
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,                    //aunque no aparece en el item, lo usamos para diferenciar cada uno de los registros
    var nombreProducto: String = "",    //nombre del producto que vamos a comprar
    var cantidad: Int = 0,              //cantidad de producto que inicialmente vamos a comprar
    var precioUnidad: Double = 0.00,    //precio de la unidad del producto
    var precioTotal: Double = 0.00      //precio total, Es la cantidad por el precioUnidad
)

