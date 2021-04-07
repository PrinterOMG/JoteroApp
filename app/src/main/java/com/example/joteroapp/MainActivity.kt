package com.example.joteroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_feed.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_to_quiz_feed.setOnClickListener {
            val quizFeedIntent = Intent(this, QuizFeed::class.java)
            startActivity(quizFeedIntent)
        }

        button_to_registration.setOnClickListener {
            val registerIntent = Intent(this, Registration::class.java)
            startActivity(registerIntent)
        }
    }
}