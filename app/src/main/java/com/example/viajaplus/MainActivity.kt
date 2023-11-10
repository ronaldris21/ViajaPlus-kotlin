package com.example.viajaplus

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.viajaplus.databinding.ActivityMainBinding
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.models.Ticket
import com.example.viajaplus.models.User
import com.example.viajaplus.services.SharedPreferenceService
import com.example.viajaplus.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import org.checkerframework.common.returnsreceiver.qual.This
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ///INICIAR MODO OSCURO/CLARO
        val sharedPreferenceService = SharedPreferenceService(this@MainActivity)
        val isCheked = sharedPreferenceService.getboolean(SingletonData.APP_THEME)
        if (isCheked)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        supportActionBar?.hide() // OCULTAR BARRA DE NAVEGACION!!!



        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // El usuario ha iniciado sesión
            if(SingletonData.retrieveUserId(this.applicationContext)==null){
                SingletonData.saveUserId(this.applicationContext,currentUser.uid)
            }
            //Log.d(ContentValues.TAG, "ProfilePictureUrl: ${currentUser.uid}")

            SingletonData.firstDate = System.currentTimeMillis()
            SingletonData.isRoundTrip = false
        }else{
            val intent = Intent(this.applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_my_trips, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}