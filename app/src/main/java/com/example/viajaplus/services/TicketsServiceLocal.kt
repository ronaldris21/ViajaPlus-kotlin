package com.example.viajaplus.services

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import com.example.viajaplus.models.Ticket
import com.example.viajaplus.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.LocalTime
import java.util.UUID

object TicketsServiceLocal {

    var tickets = mutableListOf<Ticket>()


    fun newTicket(ticket: Ticket){
        tickets.add(ticket)

        val db = Firebase.firestore

        db.collection("Tickets")
            .add(ticket)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }

    }

    suspend fun getTickets(): MutableList<Ticket> {
        //TODO: from Firebase
        val db = Firebase.firestore
        tickets = mutableListOf<Ticket>()
        Log.e("getTickets","getTickets  INIT")

        db.collection("Tickets")
            .get()
            .await()
            .forEach { document ->
                Log.d(ContentValues.TAG, "Tickets: ${document.id} => ${document.data}")
                //TODO: llenar los datos del ticket
                // Llenamos los datos del ticket

                var userid = document.data["userId"] as String
                if(userid == SingletonData.userId)
                {
                    var result = document.data["departureTime"] as HashMap<String, Long>
                    val departureTime = LocalTime.of((result["hour"] as Long).toInt(), (result["minute"] as Long).toInt())
                    result = document.data["arrivalTime"] as HashMap<String, Long>
                    val arrivalTime = LocalTime.of((result["hour"] as Long).toInt(), (result["minute"] as Long).toInt())

                    val ticket = Ticket(
                        document.id,
                        document.data["userId"] as String,
                        document.data["purchaseDate"] as Long,
                        document.data["travelDate"] as Long,
                        document.data["originCity"] as String,
                        document.data["destinationCity"] as String,
                        departureTime,
                        arrivalTime,
                        document.data["price"] as Double
                    )
                    tickets.add(ticket)
                }

            }

        Log.e("getTickets","getTickets  END")


        return tickets
    }
}
