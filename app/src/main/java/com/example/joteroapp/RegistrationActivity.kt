package com.example.joteroapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class RegistrationActivity : AppCompatActivity() {
    var login:String? = ""
    var password:String? = ""
    var name = ""
    var surname = ""
    var middleName = ""
    var city = ""
    var age = 0
    var sex = ""
    var reg = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val registerIntent = intent

        login = registerIntent.getStringExtra("login")
        password = registerIntent.getStringExtra("password")

        Log.d("check", "$login, $password") // Проверил, работаить

        button_register.setOnClickListener {
            if (inputDataCheck()) {
                val url = "https://grixa230.pythonanywhere.com/register?login=$login&password=$password&name=$name&surname=$surname&middleName=$middleName&age=$age&city=$city&sex=$sex"

                // Запросик пока выключил, ибо не тестил| включил тк я бешенный
                AsyncTaskHandlerJson().execute(url)
            }
        }

        button_quiz.setOnClickListener {
            reg = true
            if (reg){
                val toQuizIntent = Intent(this, QuizFeedActivity::class.java)
                startActivity(toQuizIntent)
            } else{
                val serverRequestToast = Toast.makeText(this@RegistrationActivity, "Вы не завершили регистрацию", Toast.LENGTH_SHORT)
                serverRequestToast.show()
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

    private fun inputDataCheck(): Boolean {
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

    private fun finalRegister(result: String) {
        if (JSONObject(result).getBoolean("register_result")) {
            val loginDataPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
            val editor = loginDataPreferences.edit()
            editor.putString("login", login)
            editor.putString("password", password)
            editor.apply()
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskHandlerJson: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            val text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection

            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            }
            finally {
                connection.disconnect()
            }

            return text
        }

        override fun onPreExecute() {
            super.onPreExecute()
            reg = true
            val serverRequestToast = Toast.makeText(this@RegistrationActivity, "Регистрация успешно завершена", Toast.LENGTH_SHORT)
            serverRequestToast.show()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (result == null) return

            if (JSONObject(result).has("register_result")) finalRegister(result)


        }

    }

}