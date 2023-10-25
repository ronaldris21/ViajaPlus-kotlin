package com.example.viajaplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.viajaplus.MainActivity
import com.example.viajaplus.R
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.ui.navbar.myTrips.flows.TicketsQrActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()


        SingletonData.retrieveUserId(this.applicationContext)
        if (SingletonData.retrieveUserId(this.applicationContext) != null )
        {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            this@LoginActivity.startActivity(intent)
            finish()
        }







        ////Logica Vista
        val btnLogin : Button = findViewById(R.id.btnLogin)
        val btnSignup : Button = findViewById(R.id.btnSignUp)

        btnLogin.setOnClickListener{

            //TODO: MACA login con Firebase


            ///Login con usuario - validaciones




            //TODO: asignar datos del user al SingletonData.currentUser

            ///Si login Succefull then this
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            this@LoginActivity.startActivity(intent)
            finish()
        }

        btnSignup.setOnClickListener{
            //Apilarla
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            this@LoginActivity.startActivity(intent)
        }


    }
}