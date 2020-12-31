package com.example.kotlintaskmanager.viewmodel

import androidx.lifecycle.*
import com.example.kotlintaskmanager.model.Task
import com.example.kotlintaskmanager.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {

    val allTasks: LiveData<List<Task>> = repository.allTasks.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun deleteAll() = viewModelScope.launch { repository.deleteAll() }

}

class TaskListViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}