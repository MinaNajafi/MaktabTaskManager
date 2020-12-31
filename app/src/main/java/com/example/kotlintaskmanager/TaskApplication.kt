package com.example.kotlintaskmanager

import android.app.Application
import com.example.kotlintaskmanager.repository.TaskRepository
import ir.najafi.cafebazaarinterview.DataBase.TaskDatabase

class TaskApplication : Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { TaskDatabase.getInstance(this) }
    val repository by lazy { TaskRepository(database.getTaskDao()) }
    
}