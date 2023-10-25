package com.example.viajaplus.ui.navbar.home.flows

import RouteListViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.example.viajaplus.R
import com.example.viajaplus.services.RoutesService
import com.example.viajaplus.services.SingletonData

class SelectRouteActivity : AppCompatActivity() {

    private var startCity : String? = ""
    private var endCity : String? = ""

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_propio_routes_sorter, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return true
    }
    fun closeStuff()
    {
        SingletonData.deleteLastTicketShoppingCart()
        finish()
    }
    override fun onBackPressed() {
        closeStuff()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_money_sort ->{
                showToast("Ordenando por precios")
            }
            R.id.menu_time_sort -> {
                showToast("Ordenando por tiempos")
            }
            android.R.id.home ->
            {
                closeStuff()
            }
            else ->
            {
                println(item.itemId)
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_route)

        val intent = intent
        if (intent.hasExtra("startCity"))
            startCity = intent.getStringExtra("startCity")

        if (intent.hasExtra("endCity"))
            endCity = intent.getStringExtra("endCity")




        val routes = RoutesService.getRoutes() // Obtén la lista de rutas desde tu servicio
        val listView = findViewById<ListView>(R.id.routes_listview)


        val desiredCities = routes.filter {
                    it.originCity.equals(startCity)
                    &&
                    it.destinationCity.equals(endCity)
        }

        supportActionBar?.title = if (SingletonData.startCity == startCity) {
            "Viaje de ida"
        } else {
            "Viaje de regreso"
        }


        val adapter = RouteListViewAdapter(this@SelectRouteActivity, desiredCities)
        listView.adapter = adapter

    }
}