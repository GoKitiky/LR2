package com.example.lr2_2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class QuizActivity : ComponentActivity() {
    private var currentPair: Pair<Int, Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizScreen()
        }
    }

    @Composable
    fun QuizScreen() {
        var number1 by remember { mutableStateOf(0) }
        var number2 by remember { mutableStateOf(0) }
        var answer by remember { mutableStateOf("") }
        var resultMessage by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Генерация новой пары чисел при первом запуске
            if (currentPair == null) {
                generateNewPair().also { currentPair = it }
                number1 = currentPair!!.first
                number2 = currentPair!!.second
            }

            Text("Умножьте: $number1 x $number2", style = MaterialTheme.typography.h2)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = answer,
                onValueChange = { answer = it },
                label = { Text("Ваш ответ") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                val correctAnswer = number1 * number2
                resultMessage = if (answer.toIntOrNull() == correctAnswer) {
                    "Правильно!"
                } else {
                    "Неправильно! Правильный ответ: $correctAnswer"
                }
            }) {
                Text("Проверить")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                generateNewPair().also { currentPair = it }
                number1 = currentPair!!.first
                number2 = currentPair!!.second
                answer = ""
                resultMessage = ""
            }) {
                Text("Новая пара")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(resultMessage, style = MaterialTheme.typography.body2)
        }
    }

    private fun generateNewPair(): Pair<Int, Int> {
        val number1 = Random.nextInt(2, 10) // Генерация числа от 2 до 9
        val number2 = Random.nextInt(1, 11) // Генерация числа от 1 до 10
        return Pair(number1, number2)
    }

    @Preview(showBackground = true)
    @Composable
    fun QuizPreview() {
        QuizScreen()
    }

}

