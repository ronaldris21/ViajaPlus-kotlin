package com.example.viajaplus.ui.navbar.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viajaplus.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        profileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //TODO: cambiar tema por ejemplo
        //TODO: Documentaicón 2 puntos
        // vistas obligatorias 3.5
        // db 1.5
        // no errores 1 puntos, valudaciones
        //Originalidad diseño
        //Puntos extras
            //
        //


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}