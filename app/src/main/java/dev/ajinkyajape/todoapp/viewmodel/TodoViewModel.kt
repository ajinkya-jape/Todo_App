package dev.ajinkyajape.todoapp.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ajinkyajape.todoapp.database.TodoDao
import dev.ajinkyajape.todoapp.database.model.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Error

/**
 * Created by Ajinkya Jape on 24/01/25.
 */

class TodoViewModel(private val todoDao: TodoDao) : ViewModel() {

    private val _todoData = MutableStateFlow<List<TodoModel>>(emptyList())
    val todoState: StateFlow<List<TodoModel>> = _todoData.asStateFlow()

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            _todoData.value = withContext(Dispatchers.IO) {
                todoDao.getAllTodos()
            }
        }
    }

    fun insertToDos(sValue: String,
                    onFailure:()->Unit,
                    onSuccess:()->Unit,
                    ) {
        viewModelScope.launch {
            try {
                if (!TextUtils.isEmpty(sValue)) {
                    withContext(Dispatchers.IO) {
                        todoDao.insertTodoItem(TodoModel(itemName = sValue))
                    }
                    fetchTodos()
                    onSuccess()
                }
            }catch (ex: Exception){
               Log.d("Error","${ex.message}")
                onFailure()
            }
        }
    }

}