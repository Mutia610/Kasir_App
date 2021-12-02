package com.mutia.kasirapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mutia.kasirapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val username = "Admin"
    private val password = "12345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login(){
        if(usernameLogin.text.isEmpty()){
            usernameLogin.error = "Username Harus Di Isi"
        }else if (passwordLogin.text.isEmpty()){
            passwordLogin.error = "Password Harus Di Isi"
        }else{
            if (usernameLogin.text.toString() == username && passwordLogin.text.toString() == password){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Username dan Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}