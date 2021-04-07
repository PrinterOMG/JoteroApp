package com.example.joteroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_registration.*

// Импорт есть

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    val button: Button = findViewById(R.id.button_register) // Пробовал так кнопку получить, но к ней (переменной) почему что нельзя обратиться

    button.setOnClickListener {Log.d("check", "ok")} // Чё за хуйня?

    register_button.setOnClickListener {Log.d("check", "ok")} // Чё за хуйня опять?

    // ПОМОГИИИИИИИИИИИИИИИИИИИИИИИИИИИИТЕ
}