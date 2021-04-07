package com.example.joteroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_registration.*


class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        button_register.setOnClickListener{
            val QuizFeedIntent = Intent(this, QuizFeed::class.java)
            startActivity(QuizFeedIntent)
        }
    }
}