package com.example.viajaplus.ui.navbar.myTrips.flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.viajaplus.R

class TicketsQrActivity : AppCompatActivity() {

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
        supportActionBar?.title = "Código Qr"
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_qr)
    }
}