package com.example.viajaplus.ui.navbar.home.flows

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



        val listView = findViewById<ListView>(R.id.ListviewTicketsShoppingCart)
            val adapter = TicketsListViewAdapter(this, SingletonData.ticketsShoppingCart)
            listView.adapter = adapter
        }

}