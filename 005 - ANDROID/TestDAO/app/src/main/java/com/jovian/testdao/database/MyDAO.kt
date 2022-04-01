package com.jovian.testdao.database

import com.jovian.testdao.database.entities.TaskEntity


//esta interfaz define las funciones que podemos hacer con la BBDD
interface MyDAO {

    fun getAllTasks(): MutableList<TaskEntity>

    fun addTask(taskEntity : TaskEntity):Long //Id of the new  task

    fun getTaskById(id: Long): TaskEntity

    fun updateTask(taskEntity: TaskEntity):Int //Number of affected rows

    fun deleteTask(taskEntity: TaskEntity):Int //Number of affected rows
}