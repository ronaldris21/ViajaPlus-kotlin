package com.example.viajaplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.viajaplus.MainActivity
import com.example.viajaplus.R
import com.example.viajaplus.services.SingletonData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    fun closeStuff(){
        finish()
    }
    override fun onBackPressed() {
        closeStuff()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                closeStuff()
            }
        }
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Registrarme"
        return true
    }

    //Instancia FirebaseAuth
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //TODO: MACA
        ////Logica Vista

        // Initialize Firebase Auth
        auth = Firebase.auth
        val signupbtn : Button = findViewById(R.id.signupbtn)

        val usernameEditText : EditText = findViewById(R.id.username)
        val emailEditText : EditText = findViewById(R.id.email)
        val passwordEditText : EditText = findViewById(R.id.password)
        val passwordreEditText : EditText = findViewById(R.id.repassword)

        signupbtn.setOnClickListener{
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val repassword = passwordreEditText.text.toString().trim()

            if(username.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()){
                // Muestra un mensaje si alguno de los campos está vacío
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
            }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Por favor, introduce una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show()
            }else if(password != repassword){
                // Muestra un mensaje si las contraseñas no coinciden
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else if(password.length<6) {
                // Muestra un mensaje si la contraseña es menor de 6 caracteres
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            }else{
                // Procede con el registro en Firebase
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            SingletonData.saveUserId(this.applicationContext,email)
                            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                            this@SignUpActivity .startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}