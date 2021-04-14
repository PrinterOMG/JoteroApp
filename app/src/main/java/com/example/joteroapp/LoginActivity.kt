package com.example.joteroapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {

    private var login: String? = ""
    private var password: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginDataPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)

        if (loginDataPreferences.contains("login") && loginDataPreferences.contains("password")) {
            login = loginDataPreferences.getString("login", "") // defValue це значение по умолчанию, то есть оно никогда не должно подставиться ибо я сделал проверку
            password = loginDataPreferences.getString("password", "")

            val url = "https://grixa230.pythonanywhere.com/login?login=$login&password=$password"
            // Запросик на сервер тута
        }


        new_login_input.addTextChangedListener(object : TextWatcher {  // Делает зеленую обводку при изменении текста в поле ввода логина
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                new_login_input.setBackgroundResource(R.drawable.correct_edit_text_style)
            }

            override fun afterTextChanged(s: Editable?) { }
        })

        new_password_input.addTextChangedListener(object : TextWatcher {  // Делает зеленую обводку при изменении текста в поле ввода пароля
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                new_password_input.setBackgroundResource(R.drawable.correct_edit_text_style)
            }

            override fun afterTextChanged(s: Editable?) { }
        })

        button_login.setOnClickListener {
            if (checkEditText()) {
                login = new_login_input.text.toString()
                password = new_password_input.text.toString()

                // Пока не обрабатывается
            }
        }

        button_register.setOnClickListener {
            if (checkEditText()) {
                login = new_login_input.text.toString()
                password = new_password_input.text.toString()

                val url = "https://grixa230.pythonanywhere.com/check_login?login=$login"
                AsyncTaskHandlerJson().execute(url)

            }
        }

    }

    private fun checkEditText(): Boolean { // Если с введёнными данными всё ок то true, иначе false
        var tempBool = true
        val lettersUp = Array(26) { i -> ('A' + i) }
        val lettersDown = Array(26) { i -> ('a' + i) }
        val letters = lettersDown + lettersUp
        if (new_login_input.length() == 0 || !(letters.contains(new_login_input.text[0])) || new_login_input.length() > 20) { // Проверяю что первый символ это буква и длину до 20
            new_login_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        }

        if (new_password_input.length() == 0 || new_password_input.length() > 20) {  // Проверяю длину от 0 до 20
            new_password_input.setBackgroundResource(R.drawable.wrong_edit_text_style)
            tempBool = false
        }

        return tempBool
    }

    private fun checkLoginOnServer(result: String) {
        val serverAnswer = JSONObject(result).getBoolean("result")

        if (serverAnswer) {
            val registerIntent = Intent(this, RegistrationActivity::class.java)
            registerIntent.putExtra("login", login)
            registerIntent.putExtra("password", password)
            startActivity(registerIntent)
        }
        else {
            new_login_input.setBackgroundResource(R.drawable.wrong_edit_text_style)

            val toast = Toast.makeText(this, "Логин занят", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
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

            val serverRequestToast = Toast.makeText(this@LoginActivity, "Получение данных с сервера...", Toast.LENGTH_SHORT)
            serverRequestToast.show()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (result == null) return

            if (JSONObject(result).has("result")) checkLoginOnServer(result)
        }

    }

}