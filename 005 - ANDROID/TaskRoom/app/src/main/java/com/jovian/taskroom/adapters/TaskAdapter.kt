package com.jovian.taskroom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovian.taskroom.R
import com.jovian.taskroom.database.entities.TaskEntity

class TasksAdapter(
    val tasks: List<TaskEntity>,
    val checkTask: (TaskEntity) -> Unit,
    val deleteTask: (TaskEntity) -> Unit) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.itemTask, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item, checkTask, deleteTask)
    }


    override fun getItemCount() = tasks.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTask = view.findViewById<TextView>(R.id.tvTask)
        val cbIsDone = view.findViewById<CheckBox>(R.id.cbIsDone)

        fun bind(task: TaskEntity, checkTask: (TaskEntity) -> Unit, deleteTask: (TaskEntity) -> Unit) {
            tvTask.text = task.name
            cbIsDone.isChecked = task.isDone
            cbIsDone.setOnClickListener{checkTask(task)}
            itemView.setOnClickListener { deleteTask(task) }
        }
    }
}