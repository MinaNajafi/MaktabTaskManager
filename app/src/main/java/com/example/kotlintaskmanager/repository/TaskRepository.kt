package com.example.kotlintaskmanager.repository

import com.example.kotlintaskmanager.model.Task
import ir.najafi.cafebazaarinterview.DataBase.TaskDao
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TaskRepository(private val taskDao: TaskDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    val allTasks: Flow<List<Task>> = taskDao.getSortedTasks()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun deleteAll() {
        taskDao.deleteAllTasks()
    }

}