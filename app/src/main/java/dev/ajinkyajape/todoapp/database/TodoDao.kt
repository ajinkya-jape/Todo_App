package dev.ajinkyajape.todoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.ajinkyajape.todoapp.database.model.TodoModel

/**
 * Created by Ajinkya Jape on 24/01/25.
 */

@Dao
interface TodoDao {
    @Query("SELECT * From tbl_todo")
    fun getAllTodos(): List<TodoModel>

    @Insert
    suspend fun insertTodoItem(todoModel: TodoModel)
}