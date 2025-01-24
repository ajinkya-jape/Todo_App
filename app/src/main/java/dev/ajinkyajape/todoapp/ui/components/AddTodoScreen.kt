package dev.ajinkyajape.todoapp.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ajinkyajape.todoapp.R
import dev.ajinkyajape.todoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Ajinkya Jape on 24/01/25.
 */

@Composable
fun AddTodoScreen(viewModel: TodoViewModel, onBack: () -> Unit) {
    var inputValue by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isValidData by remember { mutableStateOf(false) }

    BackHandler {
        onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = inputValue,
            maxLines = 1, // enter a single line of text
            onValueChange = { inputValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            placeholder = {
                Text(
                    fontSize = 13.sp,
                    text = stringResource(R.string.enter_todo)
                )
            },
            isError = isValidData
        )

        if (isValidData) {
            /*
            * Error Text - to show error message*/
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Button(
            onClick = {
                /*
                * Check input field is empty or not.*/
                if (inputValue.isBlank()) {
                    isValidData = true
                    errorMsg = "Please enter value"
                    CoroutineScope(Dispatchers.Default).launch {
                        delay(3000)
                        isValidData = false
                    }
                } else if (inputValue.contains("error", true)) {
                    isValidData = true
                    errorMsg = "Failed to add Todos"
                    CoroutineScope(Dispatchers.Default).launch {
                        delay(1000)
                        isValidData = false
                        onBack()
                    }
                } else {
                    isLoading = true
                    viewModel.insertToDos(
                        sValue = inputValue,
                        onFailure = {
                            isLoading = false
                            onBack()
                        },
                        onSuccess = {
                            isLoading = false
                            onBack()
                        }
                    )
                }

            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Todo")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }


}