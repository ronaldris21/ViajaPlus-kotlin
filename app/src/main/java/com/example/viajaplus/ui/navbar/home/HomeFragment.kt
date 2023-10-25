package com.example.viajaplus.ui.navbar.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.viajaplus.databinding.FragmentHomeBinding
import com.example.viajaplus.services.RoutesService
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.adapters.WhiteTextSpinnerAdapter
import com.example.viajaplus.ui.navbar.home.flows.SelectRouteActivity
import com.example.viajaplus.services.DatesHelperConverter
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!






    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

            _binding = FragmentHomeBinding.inflate(inflater, container, false)

            //BORRO LOS DATOS DEL "CARRITO DE COMPRAS"
            SingletonData.cleanTicketsCart()


            // Acceder a las propiedades del binding de manera segura
            val spinnerStart: Spinner = _binding!!.spinnerStartCity
            val spinnerEnd: Spinner = _binding!!.spinnerEndCity

            //CIUDADES DE INICIO
            val routes = RoutesService.getRoutes()
            val uniqueCities = routes.distinctBy { it.originCity}.map { it.originCity }
            val adapterCityOrigin = WhiteTextSpinnerAdapter(this.requireActivity().applicationContext,
                R.layout.simple_spinner_item, uniqueCities)
            spinnerStart.adapter = adapterCityOrigin


            //CIUDADES AL ELEGIR LAS CIUDADES DE FIN
            spinnerStart.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val startCity = spinnerStart.selectedItem.toString()
                    val endindCities = routes.filter { it.originCity.equals(startCity)}.map { it.destinationCity }.distinct()
                    val adapterCityDestiny = WhiteTextSpinnerAdapter(requireActivity().applicationContext,
                        R.layout.simple_spinner_item, endindCities)
                    spinnerEnd.adapter = adapterCityDestiny
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Manejar la situación en la que no se selecciona nada
                }
            }


            //BTN FECHAS
            val btnDates : Button = _binding!!.btnDates
            val checkBoxRoundTrip : CheckBox = _binding!!.checkBoxRoundTrip
            val txtFechas : TextView = _binding!!.txtFechasSeleccionadas




            btnDates.setOnClickListener{

                if (checkBoxRoundTrip.isChecked)
                {
                    val dateRangePicker =
                    MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Selecciona la fechas de viaje")
                        .setCalendarConstraints(
                            CalendarConstraints.Builder()
                                .setStart(MaterialDatePicker.todayInUtcMilliseconds())
                                .build()
                        )
                        .setSelection(
                            Pair(
                                MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds()
                            )
                        )

                        .build()

                    dateRangePicker.addOnPositiveButtonClickListener { dateRangeSelection ->
                        SingletonData.firstDate = dateRangeSelection.first
                        SingletonData.secondDate = dateRangeSelection.second
                        SingletonData.isRoundTrip = true

                        txtFechas.text = DatesHelperConverter.longToStringDate(SingletonData.firstDate) + " -> " + DatesHelperConverter.longToStringDate(SingletonData.secondDate)
                    }


                    dateRangePicker.show(this.parentFragmentManager,"Rango de fechas")
                }
                else
                {
                    val datePicker =
                        MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Selecciona la fecha de viaje")
                            .setCalendarConstraints(
                                CalendarConstraints.Builder()
                                    .setStart(MaterialDatePicker.todayInUtcMilliseconds())
                                    .build()
                            )
                            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                            .build()

                    datePicker.addOnPositiveButtonClickListener { dateSelection ->
                        SingletonData.firstDate = dateSelection
                        SingletonData.isRoundTrip = false

                        txtFechas.text = DatesHelperConverter.longToStringDate(SingletonData.firstDate)
                    }


                    datePicker.show(this.parentFragmentManager,"Una sola fecha")
                }

            }


        ///BOTON DE BUSCAR BUSES
        val btnSearch: Button = _binding!!.btnBuscarViajes

        btnSearch.setOnClickListener{
            //TODO: ME QUEDE AQUI! Has esto y vete a otra vista luego
            //Valida si los datos están bien

            //Guardar datos en el singleton
            SingletonData.isRoundTrip =  checkBoxRoundTrip.isChecked
            SingletonData.startCity = spinnerStart.selectedItem.toString()
            SingletonData.endCity = spinnerEnd.selectedItem.toString()

            Log.e("CANT_TICKETS HOME", SingletonData.ticketsShoppingCart.size.toString())


            //Llamar la nueva activity de los precios
            /** NAVEGAR ENTRE PANTALLAS ACTIVITIES **/
            val navController = findNavController()
            Log.e("CONTEXT    HOME", this@HomeFragment.context.toString())
            val intent: Intent = Intent(this@HomeFragment.context, SelectRouteActivity::class.java)
            intent.putExtra("startCity", SingletonData.startCity)
            intent.putExtra("endCity", SingletonData.endCity)

            /** Si lo haces Así vas a apilar las activities encima de otra **/
            startActivity(intent)

            /** Si lo haces así vas a crear una nueva activity independiente **/
            //startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}