package com.jovian.p5shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jovian.p5shoppinglist.database.entities.Producto

//bbdd que coge como campos las variables miembro de la clase producto
//los metodos para acceder a la BBDD con las distintas consultas DDL y demás
//estan creadas en la interfaz ProductoDao
@Database(entities = arrayOf(Producto::class), version = 1)
abstract class ProductoDatabase : RoomDatabase() {

    abstract fun productDao(): ProductoDao

    companion object{
        private var instance:ProductoDao? = null

        fun getInstance(context: Context):ProductoDao{
            return instance ?: Room.databaseBuilder(context, ProductoDatabase::class.java, "productos-db").build().productDao().also { instance = it }
        }
    }
}