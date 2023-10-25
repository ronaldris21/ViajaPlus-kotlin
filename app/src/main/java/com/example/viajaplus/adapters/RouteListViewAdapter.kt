import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.viajaplus.R
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.models.Route
import com.example.viajaplus.models.Ticket
import com.example.viajaplus.ui.navbar.home.flows.ConfirmRoutesSelectedActivity
import com.example.viajaplus.ui.navbar.home.flows.SelectRouteActivity
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

            ///Agrego el ticket al carrito
            SingletonData.addTicket(ticket)

            if(SingletonData.canIStillAddAnotherTicket())
            {
                //Cambio las ciudaddes y vuelo a buscar las ciudades

                val intent = Intent(context, SelectRouteActivity::class.java)
                intent.putExtra("startCity", SingletonData.endCity)
                intent.putExtra("endCity",SingletonData.startCity)
                this@RouteListViewAdapter.context.startActivity(intent)
            }
            else
            {

                val intent = Intent(context, ConfirmRoutesSelectedActivity::class.java)
                this@RouteListViewAdapter.context.startActivity(intent)

            }


            // Mostrar un Toast para indicar que se ha comprado el boleto

            //TODO: Validar si es round trip que ya ha comprado AMBOS!
            //TODO: si no es round trip o ya ha comprado los dos boletos mandar a validar que si esos soo los dos tickets que quiere


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
