package com.jovian.testdao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jovian.testdao.database.entities.TaskEntity

//esto es la BBDD
@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object{ //Singleton Pattern
        private var instance:TaskDAO? = null

        fun getInstance(context: Context):TaskDAO{
            return instance ?: Room.databaseBuilder(context, TasksDatabase::class.java, "tasks-db").build().taskDao().also { instance = it }
        }
    }
}