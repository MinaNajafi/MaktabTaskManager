package com.example.kotlintaskmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
class Task(
    @PrimaryKey @ColumnInfo(name = "title_col") var title: String,
    @ColumnInfo(name = "description_col") var description: String,
    @ColumnInfo(name = "done") var isCompleted : Boolean,
    @ColumnInfo(name = "doing") var isDoing : Boolean
)
//{
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0
//}


