package dev.ajinkyajape.todoapp.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ajinkyajape.todoapp.database.model.TodoModel

/**
 * Created by Ajinkya Jape on 24/01/25.
 */
@Database(entities = [TodoModel::class], version = 2, exportSchema = false)
abstract class TodoRoomDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object{

        @Volatile
        private var todoRoomDatabase: TodoRoomDatabase? = null
        fun getInstance (context : Context): TodoRoomDatabase{

            synchronized(this){
                var instance = todoRoomDatabase
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoRoomDatabase::class.java,
                        "todo_details.db"
                    ).build()
                }

                return instance
            }

        }
    }
}