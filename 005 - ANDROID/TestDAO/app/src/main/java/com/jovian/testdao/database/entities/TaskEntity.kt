package com.jovian.testdao.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//Esta es la estructura de nuestra "BBDD"
@Entity(tableName = "task_entity")
class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String = "",
    var isDone:Boolean = false
)