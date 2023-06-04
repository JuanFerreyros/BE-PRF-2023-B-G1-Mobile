package com.example.be_prf_2023_b_g1_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.example.be_prf_2023_b_g1_mobile.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener() {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)

            startActivity(intent)
        }
    }
}