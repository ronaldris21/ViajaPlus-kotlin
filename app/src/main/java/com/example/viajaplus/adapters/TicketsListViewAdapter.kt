package com.example.viajaplus.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.viajaplus.R
import com.example.viajaplus.models.Ticket
import com.example.viajaplus.services.DatesHelperConverter
import com.example.viajaplus.ui.navbar.home.flows.ConfirmRoutesSelectedActivity
import com.example.viajaplus.ui.navbar.myTrips.flows.TicketsQrActivity

class TicketsListViewAdapter(private val context: Context, private val routes: List<Ticket>) : BaseAdapter() {

    override fun getCount(): Int {
        return routes.size
    }

    override fun getItem(position: Int): Any {
        return routes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_tickets_layout, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val ticket = getItem(position) as Ticket

        // Configurar las vistas en el elemento de lista
        holder.startHourTextView.text = ticket.departureTime.toString()
        holder.startCityTextView.text = ticket.originCity
        holder.endCityTextView.text = ticket.destinationCity
        holder.endHourTextView.text = ticket.arrivalTime.toString()
        holder.priceTextView.text = "Precio de venta es €" + ticket.price.toString()
        holder.txtFecha.text = "Fecha de viaje: "+ DatesHelperConverter.longToStringDate(ticket.purchaseDate)

        holder.btnVerQr.setOnClickListener {

            val intent = Intent(context, TicketsQrActivity::class.java)
            this@TicketsListViewAdapter.context.startActivity(intent)

        }

        return view
    }


    private class ViewHolder(view: View) {
        val startCityTextView: TextView = view.findViewById(R.id.startCityTextView)
        val endCityTextView: TextView = view.findViewById(R.id.endCityTextView)
        val startHourTextView: TextView = view.findViewById(R.id.startHour)
        val endHourTextView: TextView = view.findViewById(R.id.endHour)
        val priceTextView: TextView = view.findViewById(R.id.priceTextView)
        val btnVerQr: TextView = view.findViewById(R.id.btnComprar)
        val txtFecha: TextView = view.findViewById(R.id.dateTravel)
    }
}
