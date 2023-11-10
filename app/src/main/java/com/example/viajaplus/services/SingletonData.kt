package com.example.viajaplus.services

import android.content.Context
import android.content.SharedPreferences
import com.example.viajaplus.models.Ticket
import com.example.viajaplus.models.User

object SingletonData {
    ///CONSTANTES
    private const val PREFERENCES_NAME = "MyAppPreferences"
    private const val USER_ID_KEY = "userId"



    //VARIABLES
    public var userId : String? = null
    public var currentUser : User? =null
    public val ticketsShoppingCart = mutableListOf<Ticket>()

    //PROVEEDOR DE FIREBASE
    public var provider : String? = null

    //Fechas
    public var isRoundTrip: Boolean = false
    public var firstDate: Long = 0
    public var secondDate: Long = 0


    //Ciudades
    public var startCity: String = ""
    public var endCity: String = ""



    ///LOGIN y Guardar el ID del registrado
    fun saveUserId(context: Context, userId: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID_KEY, userId)
        editor.apply()

        this.userId = userId.toString()
    }
    fun removeUserId(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(USER_ID_KEY)
        editor.apply()

        this.userId = null

    }

    fun retrieveUserId(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        this.userId = sharedPreferences.getString(USER_ID_KEY, null)
        return this.userId
    }


    fun initUser(user: User) {
        currentUser = user
        userId = user.userId
    }

    fun removeCurrentUser() {
        currentUser = null
        userId = null
    }



    //Gestion Carrito Compras Ticket
    fun addTicket(ticket: Ticket) {
        if(ticketsShoppingCart.count()==0)
        {
            ticketsShoppingCart.add(ticket)
            return
        }
        if(isRoundTrip && ticketsShoppingCart.count()<2)
        {
            ticketsShoppingCart.add(ticket)
            return
        }
    }
    fun canIStillAddAnotherTicket() :Boolean {
        if(isRoundTrip && ticketsShoppingCart.count()==2)
            return false
        if(!isRoundTrip && ticketsShoppingCart.count()==1)
            return false

        return true
    }

    fun cleanTicketsCart()
    {
        ticketsShoppingCart.clear()
    }

    fun deleteLastTicketShoppingCart()
    {
        if (ticketsShoppingCart.size>0)
        {
            ticketsShoppingCart.removeLast()
        }
    }

}