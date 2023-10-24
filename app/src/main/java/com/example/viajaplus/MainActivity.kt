package com.example.viajaplus

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.viajaplus.databinding.ActivityMainBinding
import com.example.viajaplus.dataservices.SingletonData
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
            profilePictureUrl = "https://example.com/profile-picture.jpg"
        ))

        SingletonData.initTicket( Ticket(
            ticketId = "1234567890",
            userId = "1234567890",
            purchaseDate = System.currentTimeMillis(),
            travelDate = 1652356800000L,
            originCity = "Madrid",
            destinationCity = "Barcelona",
            departureTime = LocalTime.of(10, 0),
            arrivalTime = LocalTime.of(12, 0),
            price = 50.00
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
                R.id.navigation_home, R.id.navigation_my_trips, R.id.navigation_profile, R.id.navigation_fragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}