package com.example.viajaplus.ui.navbar.home.flows

import RouteListViewAdapter
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.example.viajaplus.R
import com.example.viajaplus.dataservices.RoutesService

class SelectRouteActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_propio_routes_sorter, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Elige tu ruta"
        return true
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
                finish()
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



        val routes = RoutesService.getRoutes() // Obtén la lista de rutas desde tu servicio
        val listView = findViewById<ListView>(R.id.routes_listview)

        val adapter = RouteListViewAdapter(this, routes)
        listView.adapter = adapter












    }
}