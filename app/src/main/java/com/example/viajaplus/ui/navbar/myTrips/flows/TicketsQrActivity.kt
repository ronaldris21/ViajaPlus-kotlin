package com.example.viajaplus.ui.navbar.myTrips.flows

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.viajaplus.R
import com.example.viajaplus.services.DatesHelperConverter
import com.example.viajaplus.services.SingletonData
import qrcode.QRCode
import qrcode.color.Colors
import java.io.FileOutputStream

//qr dependencies

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



        initQrImage()

        val startCityTextView: TextView = findViewById(R.id.startCityTextView)
        val endCityTextView: TextView = findViewById(R.id.endCityTextView)
        val startHourTextView: TextView = findViewById(R.id.startHour)
        val endHourTextView: TextView = findViewById(R.id.endHour)
        val txtFecha: TextView = findViewById(R.id.dateTravel)

        startHourTextView.text = SingletonData.ticketQr?.departureTime.toString()
        startCityTextView.text = SingletonData.ticketQr?.originCity
        endCityTextView.text = SingletonData.ticketQr?.destinationCity
        endHourTextView.text = SingletonData.ticketQr?.arrivalTime.toString()
        txtFecha.text = "Fecha de viaje: "+ DatesHelperConverter.longToStringDate(SingletonData.ticketQr?.travelDate as Long)




    }

    private fun initQrImage() {

        // Obtener la referencia de ImageView
        val imgQr: ImageView = findViewById(R.id.imgQr)

        val helloWorld = QRCode.ofSquares()
            .build(SingletonData.ticketQr?.ticketId.toString()+" - "+ SingletonData.ticketQr?.originCity + " -> "+SingletonData.ticketQr?.destinationCity)

        // By default, QRCodes are rendered as PNGs.
        val pngBytes = helloWorld.render()

        // Load the image from the file
        val image = BitmapFactory.decodeByteArray(pngBytes.getBytes(),0, pngBytes.getBytes().size)

        // Establecer la imagen generada en la ImageView
        imgQr.setImageBitmap(image)
    }
}