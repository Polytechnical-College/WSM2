package com.example.wsconnectuser1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wsconnectuser1.databinding.ActivitySignInBinding
import com.example.wsconnectuser1.databinding.ActivitySignUpBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

private lateinit var binding: ActivitySignUpBinding
private var client = OkHttpClient()
private lateinit var request: Request
private lateinit var gson: Gson
private lateinit var formBody: FormBody

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }

        var url = "http://cinema.areas.su/auth/register"

        binding.signIn.setOnClickListener {

            var firstName = binding.personedit.text.toString()
            var email = binding.emailedit.text.toString()
            var password = binding.passwordedit.text.toString()
            var lastName = "1"
            formBody = FormBody.Builder()
                .add("email", email)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("password", password)
                .build()
            request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("TAG", "onFailure: ")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 201) {
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Успешная регистрация", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            })
        }
        setContentView(binding.root)
    }
}