package com.example.joteroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        new_login_input.addTextChangedListener(object : TextWatcher {  // Делает зеленую обводку при изменении текста в поле ввода логина
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { new_login_input.setBackgroundResource(R.drawable.correct_edit_text_style) }

            override fun afterTextChanged(s: Editable?) { }
        })

        new_password_input.addTextChangedListener(object : TextWatcher {  // Делает зеленую обводку при изменении текста в поле ввода пароля
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            // Нужно добавить проверку недопустимых символов
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { new_password_input.setBackgroundResource(R.drawable.correct_edit_text_style) }

            override fun afterTextChanged(s: Editable?) { }
        })

        button_login.setOnClickListener {
            if (checkEditText()) {
                val login = new_login_input.text.toString()
                val password = new_password_input.text.toString()

                // Отправка запроса на сервер
            }
        }

        button_register.setOnClickListener {
            if (checkEditText()) {
                val login = new_login_input.text.toString()
                val password = new_password_input.text.toString()

                // Добавить проверку что логин не занят

                val registerIntent = Intent(this, RegistrationActivity::class.java)
                registerIntent.putExtra("login", login)
                registerIntent.putExtra("password", password)
                startActivity(registerIntent)

                // Переход на страницу регестрации с передачей логина и пароля из текущего окна
            }
        }

    }

    private fun checkEditText(): Boolean { // Если с введёнными данными всё ок то true, иначе false
        var tempBool = true

        if (new_login_input.length() == 0) { // Нужно добавить недопустимые символы в проверку
            new_login_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        }

        if (new_password_input.length() == 0) {  // Нужно добавить недопустимые символы в проверку
            new_password_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        }

        return tempBool
    }
}