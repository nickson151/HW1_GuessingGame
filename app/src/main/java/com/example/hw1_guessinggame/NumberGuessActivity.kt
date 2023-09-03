package com.example.hw1_guessinggame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1_guessinggame.databinding.ActivityNumberGuessBinding
import kotlin.random.Random

class NumberGuessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumberGuessBinding
    private var targetNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberGuessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        targetNumber = Random.nextInt(1, 1001)

        binding.guessButton.setOnClickListener {
            checkGuess()
        }

        binding.restartButton.setOnClickListener {
            restartGame()
        }
    }

    private fun checkGuess() {
        val userGuess = binding.guessInput.text.toString().toIntOrNull()

        if (userGuess != null) {
            attempts++
            binding.attemptsText.text = "Intentos: $attempts"

            if (userGuess == targetNumber) {
                binding.resultText.text = "¡Has ganado en $attempts intentos!"
                binding.guessButton.isEnabled = false
            } else if (userGuess < targetNumber) {
                binding.resultText.text = "Más alto"
            } else {
                binding.resultText.text = "Más bajo"
            }
        } else {
            binding.resultText.text = "Ingresa un número válido."
        }
    }

    private fun restartGame() {
        targetNumber = Random.nextInt(1, 1001)
        attempts = 0
        binding.attemptsText.text = "Intentos: 0"
        binding.resultText.text = ""
        binding.guessButton.isEnabled = true
        binding.guessInput.text.clear()
    }
}
