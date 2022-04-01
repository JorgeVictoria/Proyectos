package com.jovian.test1ev

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Users {


    data class User(
        val id:Int,
        val username: String,
        val password: String
    )

    companion object{

        val listaUsers = mutableListOf<User>()

        fun loadJson(context: Context):MutableList<User>? {
            var UsersList: MutableList<User>? = null
            val raw = context.resources.openRawResource(R.raw.users)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<User?>?>() {}.type

            val gson = Gson()
            UsersList = gson.fromJson(rd, listType)

            //pasamos todos los objetos leidos del Json a la coleccion de usuarios
            if (UsersList != null) {
                listaUsers.addAll(UsersList)
            }

            return listaUsers
        }

        //getter del id del libro
        fun getUserById(id:Int):User{
            return listaUsers.filter{
                 it.id== id
            }[0]
        }
    }

    //metodo para comprobar que el usuario existe
    fun checkUser(user: User):Boolean{
       var index = 0
        var existe:Boolean = false
        while (index < listaUsers.size){
            var temp:User = listaUsers.get(index)
            if( user.username.equals(temp.username)) {
                    existe = true
            }
        }
        return existe
    }
}