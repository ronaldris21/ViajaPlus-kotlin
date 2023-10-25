package com.example.viajaplus.services

import com.example.viajaplus.models.Ticket
import java.time.LocalTime
import java.util.UUID

object TicketsServiceLocal {

    private val tickets = mutableListOf<Ticket>()

    //TODO: borrar
    init {
        // Crear dos tickets antiguos
        val oldTicket1 = Ticket(
            UUID.randomUUID().toString(),
            "userID1",
            System.currentTimeMillis() - 86400000, // Una fecha anterior a la actual (en milisegundos)
            System.currentTimeMillis() - 82800000, // Otra fecha anterior
            "CityA",
            "CityB",
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            25.0
        )

        val oldTicket2 = Ticket(
            UUID.randomUUID().toString(),
            "userID2",
            System.currentTimeMillis() - 172800000, // Otra fecha anterior a la actual
            System.currentTimeMillis() - 165600000, // Otra fecha anterior
            "CityC",
            "CityD",
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            35.0
        )

        tickets.add(oldTicket1)
        tickets.add(oldTicket2)

        // Crear dos tickets nuevos
        val newTicket1 = Ticket(
            UUID.randomUUID().toString(),
            "userID3",
            System.currentTimeMillis() + 86400000, // Una fecha posterior a la actual
            System.currentTimeMillis() + 90000000, // Otra fecha posterior
            "CityE",
            "CityF",
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            30.0
        )

        val newTicket2 = Ticket(
            UUID.randomUUID().toString(),
            "userID4",
            System.currentTimeMillis() + 172800000, // Otra fecha posterior a la actual
            System.currentTimeMillis() + 180000000, // Otra fecha posterior
            "CityG",
            "CityH",
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            40.0
        )

        tickets.add(newTicket1)
        tickets.add(newTicket2)
        tickets.add(newTicket2)
        tickets.add(newTicket2)
        tickets.add(newTicket2)
        tickets.add(newTicket2)
    }
    fun newTicket(ticket: Ticket){
        tickets.add(ticket)
    }

    fun getTickets(): List<Ticket> {
        //TODO: from Firebase
        return tickets.toList()
    }
}
