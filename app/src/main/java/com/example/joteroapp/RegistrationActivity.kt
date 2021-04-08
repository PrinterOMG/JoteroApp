package com.example.joteroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    var name = ""
    var surname = ""
    var middleName = ""
    var city = ""
    var age = 0
    var sex = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val registerIntent = intent

        val login = registerIntent.getStringExtra("login")
        val password = registerIntent.getStringExtra("password")

        Log.d("check", "$login, $password") // Проверил, работаить

        button_register.setOnClickListener {
            if (inputDataCheck()) {
                val url = "https://grixa230.pythonanywhere.com/register?login=$login&password=$password&name=$name&surname=$surname&middleName=$middleName&age=$age&city=$city&sex=$sex"

                Log.d("checker", "Отправка запросика на сервер")
            }
        }

        name_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { name_input.setBackgroundResource(R.drawable.correct_edit_text_style) }
            override fun afterTextChanged(s: Editable?) { }
        })
        surname_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { surname_input.setBackgroundResource(R.drawable.correct_edit_text_style) }
            override fun afterTextChanged(s: Editable?) { }
        })
        middleName_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { middleName_input.setBackgroundResource(R.drawable.correct_edit_text_style) }
            override fun afterTextChanged(s: Editable?) { }
        })
        city_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { city_input.setBackgroundResource(R.drawable.correct_edit_text_style) }
            override fun afterTextChanged(s: Editable?) { }
        })
        age_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { age_input.setBackgroundResource(R.drawable.correct_edit_text_style) }
            override fun afterTextChanged(s: Editable?) { }
        })

    }

    fun inputDataCheck(): Boolean {
        var tempBool = true

        if (name_input.length() == 0) {
            name_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        } else name = name_input.text.toString()

        if (surname_input.length() == 0) {
            surname_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        } else surname = surname_input.text.toString()

        if (middleName_input.length() == 0) {
            middleName_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        } else middleName = middleName_input.text.toString()

        if (city_input.length() == 0) {
            city_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        } else city = city_input.text.toString()

        if (age_input.length() == 0) {
            age_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        }
        else {
            age = age_input.text.toString().toInt()

            if (age <= 0 || age >= 100) tempBool = false
        }

        if (sex_select.checkedRadioButtonId == -1) {
            tempBool = false
        }
        else {
            sex = if (sex_select.checkedRadioButtonId == sex_male.id) "male"
            else "female"
        }

        return tempBool
    }

}