package com.jovian.p2_master_detail_books

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jovian.p2_master_detail_books.placeholder.PlaceholderContent
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.util.HashMap

data class BookItem(
    //variables miembro de la clase
    var id:String,
    var author:String,
    var description:String,
    var publication_date:String,
    var title:String,
    var url_image:String
) {
    //clase estatica
    companion object {
        //coleccion de libros
        val listaBookItem = mutableListOf<BookItem>()
        //esto lo utiliza la plantilla, asigna una clave a un objeto
        val ITEM_MAP: MutableMap<String, BookItem> = HashMap()
        //funcion para leer datos de un fichero Json
        fun loadJson(context: Context):MutableList<BookItem>?{
            var booksList:MutableList<BookItem>? = null
            val raw = context.resources.openRawResource(R.raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<BookItem?>?>() {}.type

            val gson = Gson()
            booksList = gson.fromJson(rd, listType)

            //pasamos todos los objetos leidos del Json a la coleccion de libros
            if (booksList != null) {
                listaBookItem.addAll(booksList)
            }

            //a todos los objetos le asignamos una clave
            //deberia funcionar con un bucle for, pero me daba problemas
            //PENDIENTE DE REVISAR EL USO DEL FOR
            ITEM_MAP.put("0", listaBookItem.get(0))
            ITEM_MAP.put("1", listaBookItem.get(1))
            ITEM_MAP.put("2", listaBookItem.get(2))
            ITEM_MAP.put("3", listaBookItem.get(3))
            ITEM_MAP.put("4", listaBookItem.get(4))
            ITEM_MAP.put("5", listaBookItem.get(5))
            ITEM_MAP.put("6", listaBookItem.get(6))
            ITEM_MAP.put("7", listaBookItem.get(7))
            ITEM_MAP.put("8", listaBookItem.get(8))

            return booksList
        }

        //getter del id del libro
        fun getBookItemById(id:String):BookItem{
            return listaBookItem.filter{
                it.id == id
            }[0]
        }

        //getter del primer id
        fun getFirstID(): String {
            return listaBookItem[0].id
        }
    }
}

