package com.example.viajaplus.dataservices

import com.example.viajaplus.models.Ticket
import com.example.viajaplus.models.User

object SingletonData {

    public var currentUser : User? =null
    public var currentTicket : Ticket? = null

    public var isRoundTrip: Boolean = false
    public var firstDate: Long = 0
    public var secondDate: Long = 0


    fun initUser(user: User) {
        currentUser = user
    }

    fun initTicket(ticket: Ticket) {
        currentTicket = ticket
    }


}