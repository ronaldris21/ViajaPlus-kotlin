package com.example.viajaplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.viajaplus.MainActivity
import com.example.viajaplus.R
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.ui.navbar.myTrips.flows.TicketsQrActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    //Instancia FirebaseAuth
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()


        //SingletonData.retrieveUserId(this.applicationContext)
        if (SingletonData.retrieveUserId(this.applicationContext) != null) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            this@LoginActivity.startActivity(intent)
            finish()
        }


        ////Logica Vista
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnSignup: Button = findViewById(R.id.btnSignUp)

        /*
        btnLogin.setOnClickListener {
            //TODO: MACA login con Firebase
            ///Login con usuario - validaciones
            //TODO: asignar datos del user al SingletonData.currentUser

            ///Si login Succefull then this
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            this@LoginActivity.startActivity(intent)
            finish()
        }
*/
        auth = Firebase.auth
        val emailEditText: EditText = findViewById(R.id.email)
        val passwordEditText: EditText = findViewById(R.id.password)

        btnLogin.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // Muestra un mensaje si alguno de los campos está vacío
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Procede con el login en Firebaseç
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //val user = auth.currentUser
                            //updateUI(user)
                            SingletonData.saveUserId(this.applicationContext, email)

                            //SingletonData.saveUserId(this.applicationContext,username)
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            this@LoginActivity.startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            showAlert()
                            //updateUI(null)
                        }
                    }
            }
        }

        btnSignup.setOnClickListener {
            //Apilarla
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            this@LoginActivity.startActivity(intent)
        }
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}