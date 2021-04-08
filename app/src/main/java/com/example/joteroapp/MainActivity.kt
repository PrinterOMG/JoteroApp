package com.example.joteroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_to_quiz_feed.setOnClickListener {
            val quizFeedIntent = Intent(this, QuizFeedActivity::class.java)
            startActivity(quizFeedIntent)
        }

        button_to_registration.setOnClickListener {
            val registerIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerIntent)
        }

        button_to_login.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }
}