package com.example.viajaplus

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.viajaplus.databinding.ActivityMainBinding
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.models.Ticket
import com.example.viajaplus.models.User
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        supportActionBar?.hide() // OCULTAR BARRA DE NAVEGACION!!!



        /////INICIALIZO DATOS DEL SINGLETON
        //TODO: Revisar INICIO DE SESION AL MOMENTO DE INICIAR LOS DATOS
        SingletonData.initUser( User(
            userId = "1234567890",
            email = "ronald.ris@example.com",
            displayName = "Ronald Ris",
            profilePictureUrl = "https://example.com/profile-picture.jpg",
            password = "xdxdxdxd"
        ))

        SingletonData.firstDate = System.currentTimeMillis()
        SingletonData.isRoundTrip = false




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