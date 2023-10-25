package com.example.viajaplus.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


class LongDateHelper {

    fun longToStringDate(dateInMillis: Long): String {
        val instant = Instant.ofEpochMilli(dateInMillis)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val day = localDateTime.dayOfMonth
        val month = localDateTime.monthValue
        val year = localDateTime.year

        // Crear una cadena con la fecha en el formato deseado
        val formattedDate = "${day} ${getMonthName(month)} ${year}"

        // Retornar la cadena con la fecha
        return formattedDate
    }

    private fun getMonthName(month: Int): String {
        val months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
        return months[month - 1]
    }
}