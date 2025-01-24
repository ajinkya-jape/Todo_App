package dev.ajinkyajape.todoapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ajinkya Jape on 24/01/25.
 */

@Entity(tableName = "tbl_todo")
data class TodoModel(
    @PrimaryKey (autoGenerate = true)
    val itemId: Int = 0,
    val itemName:String
)