package dev.ajinkyajape.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dev.ajinkyajape.todoapp.database.TodoRoomDatabase
import dev.ajinkyajape.todoapp.ui.components.AddTodoScreen
import dev.ajinkyajape.todoapp.ui.components.HomeScreen
import dev.ajinkyajape.todoapp.ui.theme.TodoAppTheme
import dev.ajinkyajape.todoapp.viewmodel.TodoViewModel
import dev.ajinkyajape.todoapp.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoRoomDatabase = TodoRoomDatabase.getInstance(context = this@MainActivity)
        val todoViewModelFactory = TodoViewModelFactory(todoRoomDatabase.todoDao())
        todoViewModel = ViewModelProvider(this,todoViewModelFactory)[TodoViewModel::class.java]

        setContent {
            /*
            * Helps to track current screen*/
            var currentScreen by remember { mutableStateOf("main") }
            TodoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        "main" -> {
                            HomeScreen(viewModel = todoViewModel) { currentScreen = "add" }
                        }
                        "add" -> {
                            AddTodoScreen(viewModel = todoViewModel) { currentScreen = "main" }
                        }
                    }
                }
            }
        }
    }
}
