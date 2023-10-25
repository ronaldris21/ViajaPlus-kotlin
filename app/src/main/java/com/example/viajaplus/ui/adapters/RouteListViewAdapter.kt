import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.viajaplus.R
import com.example.viajaplus.dataservices.SingletonData
import com.example.viajaplus.dataservices.TicketsServiceLocal
import com.example.viajaplus.models.Route
import com.example.viajaplus.models.Ticket
import java.util.UUID

class RouteListViewAdapter(private val context: Context, private val routes: List<Route>) : BaseAdapter() {

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
            view = LayoutInflater.from(context).inflate(R.layout.list_route_item_layout, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val route = getItem(position) as Route

        // Configurar las vistas en el elemento de lista
        holder.startHourTextView.text = route.departureTime.toString()
        holder.startCityTextView.text = route.originCity
        holder.endCityTextView.text = route.destinationCity
        holder.endHourTextView.text = route.arrivalTime.toString()
        holder.priceTextView.text = "Precio de venta es €" + route.price.toString()

        holder.btnComprar.setOnClickListener {
            //TODO: poner ID DEL USUARIO
            //TODO: poner fecha de viaje
            //TODO: ASIGNAR TicketID
            val ticket = Ticket(
                ticketId = UUID.randomUUID().toString(),
                userId = "USUARIO ID", // Usar el usuario proporcionado al construir el adaptador
                purchaseDate = System.currentTimeMillis(), // Fecha actual en milisegundos
                travelDate = SingletonData.firstDate, // TODO: Validar si es la primera o segunda fecha
                originCity = route.originCity,
                destinationCity = route.destinationCity,
                departureTime = route.departureTime,
                arrivalTime = route.arrivalTime,
                price = route.price
            )

            // Agregar el ticket a TicketsServiceLocal
            TicketsServiceLocal.newTicket(ticket)

            // Mostrar un Toast para indicar que se ha comprado el boleto
            Toast.makeText(context, "Boleto comprado: ${route.originCity} a ${route.destinationCity}", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private class ViewHolder(view: View) {
        val startCityTextView: TextView = view.findViewById(R.id.startCityTextView)
        val endCityTextView: TextView = view.findViewById(R.id.endCityTextView)
        val startHourTextView: TextView = view.findViewById(R.id.startHour)
        val endHourTextView: TextView = view.findViewById(R.id.endHour)
        val priceTextView: TextView = view.findViewById(R.id.priceTextView)
        val btnComprar: TextView = view.findViewById(R.id.btnComprar)
    }
}
