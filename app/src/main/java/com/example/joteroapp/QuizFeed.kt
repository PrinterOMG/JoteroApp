package com.example.joteroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_quiz_feed.*

class QuizFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_feed)
        available_quiz_body.setOnClickListener {
            val QuizIntent = Intent(this, MainActivity::class.java)
            startActivity(QuizIntent)
        }
    }
}