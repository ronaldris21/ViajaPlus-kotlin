package com.example.viajaplus.ui.navbar.myTrips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viajaplus.databinding.FragmentMyTripsBinding
import com.example.viajaplus.services.TicketsService
import com.example.viajaplus.adapters.TicketsListViewAdapter
import com.example.viajaplus.adapters.TicketsQRListViewAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.runBlocking

class MyTripsFragment : Fragment() {

    private var _binding: FragmentMyTripsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MyTripsViewModel::class.java)

        _binding = FragmentMyTripsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tabLayout: TabLayout = binding.TabLayoutTicketSelector

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Manejar cambios de pestaña aquí
                when (tab?.position) {
                    0 -> showUpcomingTickets()
                    1 -> showOldTickets()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Manejar pestaña no seleccionada si es necesario
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Manejar pestaña reseleccionada si es necesario
            }
        })

        Toast.makeText(requireContext(), "Cargando tus tickets", Toast.LENGTH_SHORT).show()

        runBlocking {
            TicketsService.getTickets()
        }


        showUpcomingTickets() //Por defecto muestra estos

        return root
    }


    fun showUpcomingTickets() {
        var ticketsMostrar = TicketsService.tickets

        // Actualizar la vista en una coroutine
            val upcomingTickets = ticketsMostrar.filter { it.travelDate >= System.currentTimeMillis() }.toMutableList()

            val listView = binding.ListviewUpcomingTickets

            val adapter = TicketsQRListViewAdapter(requireContext(), upcomingTickets)
            listView.adapter = adapter
    }
    private fun showOldTickets() {
        // Obtener los tickets de manera asíncrona
        var ticketsMostrar = TicketsService.tickets
        val oldTickets = ticketsMostrar.filter { it.travelDate < System.currentTimeMillis() }.toMutableList()

        val listView = binding.ListviewUpcomingTickets

        val adapter = TicketsQRListViewAdapter(requireContext(), oldTickets)
        listView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}