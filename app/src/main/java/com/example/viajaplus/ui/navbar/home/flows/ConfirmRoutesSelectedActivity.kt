package com.example.viajaplus.ui.navbar.home.flows

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.viajaplus.R
import com.example.viajaplus.adapters.TicketsListViewAdapter
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.services.TicketsServiceLocal
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.example.viajaplus.MainActivity

class ConfirmRoutesSelectedActivity : AppCompatActivity() {

    fun closeStuff(){
        SingletonData.deleteLastTicketShoppingCart()
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
        supportActionBar?.title = "Confirma tus tickets"
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_routes_selected)


        val txtPrice = findViewById<TextView>(R.id.txtPricing)
        txtPrice.text = "Precio total: € " + String.format("%.2f", SingletonData.ticketsShoppingCart.sumOf { it.price })

        val listView = findViewById<ListView>(R.id.ListviewTicketsShoppingCart)
        val adapter = TicketsListViewAdapter(this, SingletonData.ticketsShoppingCart)
        listView.adapter = adapter

        val btnConfirmarCompra = findViewById<Button>(R.id.btnComprar)
        btnConfirmarCompra.setOnClickListener{

            SingletonData.ticketsShoppingCart.forEach { ticket ->
                TicketsServiceLocal.newTicket(ticket)
            }


            SingletonData.ticketsShoppingCart.clear()
            Toast.makeText(this, "TICKETS COMPRADOS EXITOSAMENTE", Toast.LENGTH_SHORT).show()



            // Get the intent to start the new activity.
            val intent = Intent(this, MainActivity::class.java)
            // Set the flags to force the new activity to be the top activity.
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // Start the new activity.
            startActivity(intent)

        }

    }


}