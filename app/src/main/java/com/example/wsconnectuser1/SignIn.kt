package com.example.wsconnectuser1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wsconnectuser1.data.Login
import com.example.wsconnectuser1.databinding.ActivitySignInBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

private lateinit var binding: ActivitySignInBinding
private var client = OkHttpClient()
private lateinit var request: Request
private lateinit var gson: Gson
private lateinit var formBody: FormBody

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
        binding.signIn.setOnClickListener {
            var url = "http://cinema.areas.su/auth/login"
            var emailvalid = Regex("[0-9a-z]@[a-z0-9]+\\.+[a-z]{1,3}$")
            var email = binding.emailedit.text.toString()
            var password = binding.emailedit.text.toString()
            formBody = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build()
            request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("TAG", "onFailure: ")
                }

                override fun onResponse(call: Call, response: Response) {
                    if(response.code == 200){
                        runOnUiThread {
                            gsonBuilder(response.body!!.string())
                        }
                    }
                    else{
                        runOnUiThread {
                            Toast.makeText(this@SignIn, "Ошибка", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }

        setContentView(binding.root)
        gson = Gson()
    }
    private lateinit var token: Login
    private fun gsonBuilder(str: String){
        token = gson.fromJson(str, Login::class.java)
        Toast.makeText(this, "Токен ${token.token}", Toast.LENGTH_SHORT).show()
    }

}