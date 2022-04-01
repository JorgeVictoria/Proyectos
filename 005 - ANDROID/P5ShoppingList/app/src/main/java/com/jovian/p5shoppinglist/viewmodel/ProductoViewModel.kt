package com.jovian.p5shoppinglist.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jovian.p5shoppinglist.database.ProductoDao
import com.jovian.p5shoppinglist.database.ProductoDatabase
import com.jovian.p5shoppinglist.database.entities.Producto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import kotlin.IllegalArgumentException
import kotlin.NumberFormatException


class ProductoViewModel(application: Application): AndroidViewModel(application) {

    //variables locales
    val context = application
    //instanciamos la BBDD
    var productoDao:ProductoDao = ProductoDatabase.getInstance(context)

    //Mutables para controlar los distintos cambios en la app
    val productoListLD: MutableLiveData<MutableList<Producto>> = MutableLiveData()
    val updateProductoLD:MutableLiveData<Producto?> = MutableLiveData()
    val deleteProductoLD:MutableLiveData<Int> = MutableLiveData()
    val insertProductoLD:MutableLiveData<Producto> = MutableLiveData()
    val totalCompraLD:MutableLiveData<Double> = MutableLiveData<Double>()

    /**
     * mandamos toda la lista de productos
     * posteriormente llamamos a la funcion calcularTotal()
     * para que calcule el importe de la compra con los productos que tenemos
     */
    fun getAllProductos(){
        CoroutineScope(Dispatchers.IO).launch {
            productoListLD.postValue(productoDao.getAllProductos())
        }
        calcularTotal()

    }

    /**
     * funcion para añadir un producto a la BBDD
     * @param nomProducto: nombre del producto
     * @param uniProducto: precio del producto
     * @param cantProducto: cantidad de producto que necesitamos comprar
     */
    fun add(nomProducto: String, uniProducto: String, cantProducto: String) {

        //comprobamos que los valores de los campos uniProducto y cantProducto sean correctos
        //en caso positivo se añade el producto a la lista
        var enteroCorrecto:Int = comprobarEntero(cantProducto)
        var doubleCorrecto:Double = comprobarDouble(uniProducto)
        //si los datos son incorrectos, reciben 0 como valor
        //y no se podra crear el item
        //lo mismo ocurre si el usario pone un valor negativo
        if(enteroCorrecto > 0 && doubleCorrecto > 0) {
            //si los valores son correctos, creamos el nuevo registro
            CoroutineScope(Dispatchers.IO).launch {
                val id = productoDao.addProducto(
                    Producto(
                        nombreProducto = nomProducto,
                        precioUnidad = uniProducto.toDouble(),
                        cantidad = cantProducto.toInt(),
                        precioTotal = calculaPrecioTotalProducto(uniProducto, cantProducto)
                    )
                )

                //una vez insertado el producto, calculamos el importe total de la compra
                calcularTotal()

                val recoveryTask = productoDao.getProductoId(id)
                insertProductoLD.postValue(recoveryTask)
            }
        }

        else Toast.makeText(context,"Los datos introducidos son incorrectos",Toast.LENGTH_SHORT).show()
    }

    /**
     * funcion para comprobar si un valor es Double
     * @param uniProducto
     * @return 0 como valor si el dato no es correcto
     */
    private fun comprobarDouble(uniProducto: String): Double {
        var a: Double = 0.0
        try {
            a = uniProducto.toDouble()
        } catch (e: NumberFormatException) {
            a = 0.0
        } catch (e: IllegalArgumentException) {
            a = 0.0
        }
        return a
    }

    /**
     * funcion para comprobar si un valor es Entero
     * @param
     * @return 0, si el dato es incorrecto
     */
    private fun comprobarEntero(unaCantidad: String): Int {
        return try {
            Integer.parseInt(unaCantidad)
        } catch (e: NumberFormatException) {
            0
        } catch (e: IllegalArgumentException){
            0
        }
    }

    /**
     * funcion para borrar un producto
     * @param producto: el producto a borrar
     */
    fun delete(producto: Producto){
        CoroutineScope(Dispatchers.IO).launch {

            val res = productoDao.borrarProducto(producto)
            calcularTotal()
            if(res>0)
                deleteProductoLD.postValue(producto.id)
            else{
                deleteProductoLD.postValue(-1)
            }


        }
    }

    /**
     * funcion para actualizar un registro cada vez que pulsemos sobre la papelera en un producto
     * @param producto, un Producto de la lista
     */
    fun update(producto:Producto){
        CoroutineScope(Dispatchers.IO).launch {

            //al pulsar sobre el boton de la papelera
            //restamos una unidad del producto
            producto.cantidad--
            //recalculamos el precio del producto
            producto.precioTotal = producto.cantidad * producto.precioUnidad
            //recalculamos el precio total de la compra
            calcularTotal()

            val res = productoDao.updateProducto(producto)
            //si la cantidad del producto es 0, lo borramos
            if(producto.cantidad <= 0) delete(producto)
            else getAllProductos()

            if(res>0)
                updateProductoLD.postValue(producto)
            else
                updateProductoLD.postValue(null)
        }
    }

    /**
     * funcion para calcular el precio totol de un producto
     * @param uniProducto, precio de la unidad
     * @param cantProducto, total de unidades del producto
     */
    fun calculaPrecioTotalProducto(uniProducto: String, cantProducto: String): Double{

        return uniProducto.toDouble() * cantProducto.toDouble()

    }

    /**
     * funcion para calcular el importe total de la compra
     * le aplicamos un sleep para que de tiempo a cargar la activity
     * en caso contrario nos bloquea la ejecucion de la app
     */
    fun calcularTotal(){
        CoroutineScope(Dispatchers.IO).launch {
            sleep(1000)
            totalCompraLD.postValue(productoDao.getTotal())
        }
    }

}