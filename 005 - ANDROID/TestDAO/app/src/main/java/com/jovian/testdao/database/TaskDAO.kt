package com.jovian.testdao.database

import androidx.room.*
import com.jovian.testdao.database.entities.TaskEntity

//interfaz que simula las distintas operaciones SQL
@Dao
interface TaskDAO:MyDAO {

    @Query("SELECT * FROM task_entity")
    override fun getAllTasks(): MutableList<TaskEntity>

    @Insert
    override fun addTask(taskEntity : TaskEntity):Long

    @Query("SELECT * FROM task_entity WHERE id LIKE :id")
    override fun getTaskById(id: Long): TaskEntity

    @Update
    override fun updateTask(taskEntity: TaskEntity):Int

    @Delete
    override fun deleteTask(taskEntity: TaskEntity):Int
}