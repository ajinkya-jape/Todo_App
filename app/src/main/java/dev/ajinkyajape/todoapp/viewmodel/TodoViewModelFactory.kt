package dev.ajinkyajape.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ajinkyajape.todoapp.database.TodoDao

/**
 * Created by Ajinkya Jape on 24/01/25.
 */

class TodoViewModelFactory (private val dao: TodoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}