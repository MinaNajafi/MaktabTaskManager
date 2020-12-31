package com.example.kotlintaskmanager.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintaskmanager.R
import com.example.kotlintaskmanager.model.Task

class TaskListAdapterOldStyle : RecyclerView.Adapter<TaskListAdapterOldStyle.TaskItemViewHolder>() {
    var data = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(
                R.layout.recyclerview_item,
                parent, false
            )
        return TaskItemViewHolder(view)
    }




    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item =  data[position]
        holder.titleTextView.text = item.title
//        TODO("Not yet implemented")

    }

    class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleTextView : TextView = itemView.findViewById(R.id.tv_title_task)
    }

    override fun getItemCount() = data.size


}

