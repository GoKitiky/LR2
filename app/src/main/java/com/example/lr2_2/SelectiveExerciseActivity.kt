package com.example.lr2_2

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
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class SelectiveExerciseActivity : ComponentActivity() {
    private var generatedNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelectiveExerciseScreen()
        }
    }

    @Composable
    fun SelectiveExerciseScreen() {
        var userInput by remember { mutableStateOf("") }
        var resultMessage by remember { mutableStateOf("") }
        var answer by remember { mutableStateOf("") }

        // Получаем значение из Intent
        val receivedInput = intent.getStringExtra("USER_INPUT")
        if (receivedInput != null) {
            userInput = receivedInput
        }

        // Генерация случайного числа при первом запуске
        if (generatedNumber == 0) {
            generatedNumber = generateRandomNumber()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Умножьте: $userInput x $generatedNumber",
                style = MaterialTheme.typography.h2
            )

            Spacer(modifier = Modifier.height(16.dp))

            var userInputSel by remember { mutableStateOf("") }

            OutlinedTextField(
                value = userInputSel,
                onValueChange = { userInputSel = it },
                label = { Text("Введите ваше число") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                val userNumber = userInput.toIntOrNull()
                if (userNumber != null) {
                    answer = userInputSel
                    val correctAnswer = userNumber * generatedNumber
                    resultMessage = if (answer.toIntOrNull() == correctAnswer) {
                        "Правильно!"
                    } else {
                        "Неправильно! Правильный ответ: $correctAnswer"
                    }
                }
            }) {
                Text("Проверить")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                generatedNumber = generateRandomNumber()
                answer = ""
                resultMessage = ""
            }) {
                Text("Новое упражнение")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(resultMessage, style = MaterialTheme.typography.body2)
        }
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(2, 10) // Генерация числа от 2 до 9
    }
}
