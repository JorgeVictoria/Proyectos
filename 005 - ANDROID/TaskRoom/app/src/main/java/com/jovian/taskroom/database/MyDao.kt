package com.jovian.taskroom.database

import com.jovian.taskroom.database.entities.TaskEntity

interface MyDao {

    fun getAllTasks(): MutableList<TaskEntity> //recover all the tasks

    fun addTask(taskEntity: TaskEntity):Long //id of the new task

    fun getTaskById(id: Long): TaskEntity

    fun updateTask(taskEntity: TaskEntity):Int //number of affected rows

    fun deleteTask(taskEntity: TaskEntity):Int //number of affected rows

}