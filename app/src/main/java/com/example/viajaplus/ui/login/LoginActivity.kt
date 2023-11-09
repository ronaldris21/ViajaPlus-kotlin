package com.example.viajaplus.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.viajaplus.MainActivity
import com.example.viajaplus.R
import com.example.viajaplus.models.User
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.ui.navbar.myTrips.flows.TicketsQrActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
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

        //Lógica Firebase
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
                // Procede con el login en Firebase
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            //Recibe el valor del User de la bbdd y lo establece en singleton
                            val db = Firebase.firestore

                            val userId = auth.currentUser?.uid
                            db.collection("Users")
                                .whereEqualTo("userId", userId)
                                .get()
                                .addOnSuccessListener { documents ->
                                    for (document in documents) {
                                        Log.d(TAG, "${document.id} => ${document.data}")
                                        val user = User(
                                            userId = userId.toString(),
                                            email = document.getString("email").toString(),
                                            password = document.getString("password").toString(),
                                            displayName = document.getString("displayName").toString(),
                                            profilePictureUrl = document.getString("profilePictureUrl").toString()
                                        )
                                        SingletonData.initUser(user)
                                        SingletonData.saveUserId(this.applicationContext, user.userId)
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.d(TAG, "get failed with ", exception)
                                    Toast.makeText(this, "Error al recibir los datos", Toast.LENGTH_SHORT).show()
                                }

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            this@LoginActivity.startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            showAlert()
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