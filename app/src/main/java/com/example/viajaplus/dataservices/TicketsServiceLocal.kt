package com.example.viajaplus.dataservices

import com.example.viajaplus.models.Route
import com.example.viajaplus.models.Ticket
import java.util.UUID

object TicketsServiceLocal {

    private val tickets = mutableListOf<Ticket>()

    fun newTicket(ticket: Ticket){
        tickets.add(ticket)
    }

    fun getTickets(): List<Ticket> {
        return tickets.toList()
    }
}
