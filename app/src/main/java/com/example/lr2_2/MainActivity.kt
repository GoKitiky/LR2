package com.example.lr2_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.Intent
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiplicationTableApp()
        }
    }
}

@Composable
fun MultiplicationTableApp() {
    var inputNumber by remember { mutableStateOf(TextFieldValue("")) }

    var result by remember { mutableStateOf("") }
    val context = LocalContext.current // Получаем контекст здесь

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Таблица умножения", style = MaterialTheme.typography.h3)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.table),
            contentDescription = "Таблица умножения",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputNumber,
            onValueChange = { inputNumber = it },
            label = { Text("Введите число от 2 до 9") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка "Упражнение для всех чисел"
        Button(onClick = {
            // Открытие новой активности
            val intent = Intent(context, QuizActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Упражнение для всех чисел")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка "Упражнение выборочно"
        Button(onClick = {
            val intent2 = Intent(context, SelectiveExerciseActivity::class.java).apply {
                putExtra("USER_INPUT", inputNumber.text)
            }
            context.startActivity(intent2)
        }) {
            Text("Упражнение выборочно")
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(result, style = MaterialTheme.typography.body2)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MultiplicationTableApp()
}