package com.example.viajaplus.models

import java.time.LocalTime

data class Route(
    var routeId: Int,
    var originCity: String,
    var destinationCity: String,
    var departureTime: LocalTime,
    var arrivalTime: LocalTime,
    var price: Double
)