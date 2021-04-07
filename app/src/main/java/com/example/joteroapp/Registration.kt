package com.example.joteroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_registration.*


class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        button_register.setOnClickListener {
            if (name_input.length() == 0) name_input.setHintTextColor(getColor(R.color.red))
            if (surname_input.length() == 0) surname_input.setHintTextColor(getColor(R.color.red))
            if (middleName_input.length() == 0) middleName_input.setHintTextColor(getColor(R.color.red))
            if (age_input.length() == 0) age_input.setHintTextColor(getColor(R.color.red))
            if (city_input.length() == 0) city_input.setHintTextColor(getColor(R.color.red))
            if (login_input.length() == 0) login_input.setHintTextColor(getColor(R.color.red))
            if (password_input.length() == 0) password_input.setHintTextColor(getColor(R.color.red))
        }

        name_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name_input.setHintTextColor(getColor(R.color.black))
                name_input.setTextColor(getColor(R.color.black))
            }

            override fun afterTextChanged(s: Editable?) { }
        })

    }
}