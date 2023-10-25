package com.example.viajaplus.ui.navbar.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viajaplus.MainActivity
import com.example.viajaplus.databinding.FragmentProfileBinding
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.ui.login.LoginActivity

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

        val btnCloseSesion : Button = binding.button
        btnCloseSesion.setOnClickListener{
            SingletonData.removeUserId(requireContext())

            requireActivity().finish()

            val intent = Intent(requireActivity(), LoginActivity::class.java)
            this@ProfileFragment.startActivity(intent)

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