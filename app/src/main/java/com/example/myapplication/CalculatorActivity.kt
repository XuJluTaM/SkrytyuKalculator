package com.example.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener { calculate('+') }
        binding.buttonSubtract.setOnClickListener { calculate('-') }
        binding.buttonMultiply.setOnClickListener { calculate('*') }
        binding.buttonDivide.setOnClickListener { calculate('/') }

        binding.buttonSendResult.setOnClickListener {
            val result = binding.textViewResult.text.toString()
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun calculate(operation: Char) {
        val number1 = binding.editTextNumber1.text.toString().toDoubleOrNull()
        val number2 = binding.editTextNumber2.text.toString().toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (operation) {
                '+' -> number1 + number2
                '-' -> number1 - number2
                '*' -> number1 * number2
                '/' -> if (number2 != 0.0) number1 / number2 else "На ноль делить нельзя"
                else -> "Ошибка"
            }
            binding.textViewResult.text = result.toString()
        } else {
            binding.textViewResult.text = "Введите оба числа"
        }
    }
}
