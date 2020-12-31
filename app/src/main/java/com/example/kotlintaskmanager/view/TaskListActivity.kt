package com.example.kotlintaskmanager.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintaskmanager.viewmodel.TaskListViewModel
import com.example.kotlintaskmanager.R
import com.example.kotlintaskmanager.State
import com.example.kotlintaskmanager.TaskApplication
import com.example.kotlintaskmanager.databinding.ActivityTaskListBinding
import com.example.kotlintaskmanager.model.Task
import com.example.kotlintaskmanager.view.adapters.TaskListAdapter
import com.example.kotlintaskmanager.viewmodel.TaskListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListActivity : AppCompatActivity() {

//    private lateinit var viewModelTemp :ViewModel
    private val viewModel: TaskListViewModel by viewModels {
        TaskListViewModelFactory((application as TaskApplication).repository)
    }

    private lateinit var binding: ActivityTaskListBinding
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_list)

//        viewModelTemp = ViewModelProvider(this, TaskListViewModelFactory((application as TaskApplication).repository)).get(TaskListViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_tasks_list)
        val adapter = TaskListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabClicked()

        viewModel.allTasks.observe(owner = this) { tasks ->
            // Update the cached copy of the taska in the adapter.
            tasks.let { adapter.submitList(it) }
        }


        binding.fabPlusTask.setOnClickListener {
            dialog.show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.delete) {
            viewModel.deleteAll()
            return true
        }

        return super.onOptionsItemSelected(item)

    }


    fun fabClicked() {

        dialog = AlertDialog.Builder(
            this
        )
            .setCancelable(false)
            .setPositiveButton("add", DialogInterface.OnClickListener { d, which ->
                dialog.dismiss()
                val titleEt = dialog.findViewById<EditText>(R.id.et_title_task)
                val stateEt = dialog.findViewById<EditText>(R.id.et_state)
                val task: Task
                task = when (stateEt?.text.toString()) {
                    "0" -> Task(titleEt?.text.toString(), "", false, false)
                    "1" -> Task(titleEt?.text.toString(), "", false, true)
                    "2" -> Task(titleEt?.text.toString(), "", true, false)
                    else -> Task("", "", false, false)
                }
                viewModel.insertTask(task)
            })
            .setNegativeButton(
                "cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            .setView(R.layout.dialog_add_word)
            .create()

    }
}