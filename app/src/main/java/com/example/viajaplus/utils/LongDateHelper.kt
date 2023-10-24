package com.example.viajaplus.utils

import java.util.Date

class LongDateHelper {

    fun longToStringDate(dateInMillis: Long): String {
        // Convertir el valor de long a Date
        val date = Date(dateInMillis)

        // Obtener el día, el mes y el año de la fecha
        val dayOfMonth = date.day
        val month = date.month + 1
        val year = date.year

        // Crear una cadena con la fecha en el formato deseado
        val formattedDate = "${dayOfMonth} ${getMonthName(month)} ${year}"

        // Retornar la cadena con la fecha
        return formattedDate
    }

    private fun getMonthName(month: Int): String {
        val months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
        return months[month - 1]
    }
}