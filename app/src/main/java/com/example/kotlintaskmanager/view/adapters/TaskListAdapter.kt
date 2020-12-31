package com.example.kotlintaskmanager.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintaskmanager.R
import com.example.kotlintaskmanager.State
import com.example.kotlintaskmanager.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class TaskViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTitle: TextView = itemView.findViewById(R.id.tv_title_task)
        private val taskState: TextView = itemView.findViewById(R.id.tv_state)
//        private val taskDescription: TextView = itemView.findViewById(R.id.)

        fun bind(text: Task) {
            taskTitle.text = text.title
//            taskDescription.text = text.description
            taskState.text = if (!(text.isCompleted && text.isDoing)) {
                State.TODO.name
            } else if (text.isCompleted && text.isDoing) {
                State.DONE.name
            } else {
                State.ISDOING.name
            }


        }


        companion object {
            fun create(parent: ViewGroup): TaskViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return TaskViewHolder(view)
            }
        }

    }
    class TaskComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.title == newItem.title
        }
    }

}