package com.example.viajaplus.models

import java.time.LocalTime

data class Ticket(
    var ticketId: String,
    var userId: String,  // ID del usuario que compró el boleto
    var purchaseDate: Long,
    var travelDate: Long,

    //Route Data
    var originCity: String,
    var destinationCity: String,
    var departureTime: LocalTime,
    var arrivalTime: LocalTime,
    var price: Double,




)