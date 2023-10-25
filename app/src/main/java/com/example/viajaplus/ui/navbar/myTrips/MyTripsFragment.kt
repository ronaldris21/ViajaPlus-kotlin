package com.example.viajaplus.ui.navbar.myTrips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viajaplus.R
import com.example.viajaplus.databinding.FragmentMyTripsBinding
import com.example.viajaplus.dataservices.RoutesService
import com.example.viajaplus.dataservices.SingletonData
import com.example.viajaplus.dataservices.TicketsServiceLocal
import com.example.viajaplus.ui.adapters.OldTicketsListViewAdapter
import com.example.viajaplus.ui.adapters.UpcomingTicketsListViewAdapter
import com.google.android.material.tabs.TabLayout

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

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

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



        showUpcomingTickets() //Por defecto muestra estos






        return root
    }

    private fun showUpcomingTickets() {
        val ticketsMostrar = TicketsServiceLocal.getTickets()
        val upcomingTickets = ticketsMostrar.filter { it.travelDate >= System.currentTimeMillis() }.toMutableList()

        val listView = binding.ListviewUpcomingTickets

        val adapter = UpcomingTicketsListViewAdapter(requireContext(), upcomingTickets)
        listView.adapter = adapter
    }

    private fun showOldTickets() {
        val ticketsMostrar = TicketsServiceLocal.getTickets()
        val oldTickets = ticketsMostrar.filter { it.travelDate < System.currentTimeMillis() }.toMutableList()

        val listView = binding.ListviewUpcomingTickets

        val adapter = OldTicketsListViewAdapter(requireContext(), oldTickets)
        listView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}