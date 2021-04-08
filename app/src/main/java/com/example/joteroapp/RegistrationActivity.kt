package com.example.joteroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val registerIntent = intent

        val login = registerIntent.getStringExtra("login")
        val password = registerIntent.getStringExtra("password")

        Log.d("check", "$login, $password") // Проверил, работаить

    }
}