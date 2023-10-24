package com.example.viajaplus.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.viajaplus.R
import com.example.viajaplus.databinding.FragmentBlankBinding
import com.example.viajaplus.databinding.FragmentHomeBinding
import com.example.viajaplus.ui.login.SignUpActivity
import com.example.viajaplus.ui.views.Setting

class BlankFragment : Fragment() {

    companion object {
        fun newInstance() = BlankFragment()
    }

    private var _binding: FragmentBlankBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: BlankViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Accede al botón de manera segura
        binding.button?.setOnClickListener {

            /** NAVEGAR ENTRE PANTALLAS ACTIVITIES **/
            val navController = findNavController()
            val intent:Intent = Intent(requireActivity(), SignUpActivity::class.java)

            /** Si lo haces Así vas a apilar las activities encima de otra **/
            //requireActivity().startActivity(intent)

            /** Si lo haces así vas a crear una nueva activity independiente **/
            startActivity(intent)


        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
        // TODO: Use the ViewModel
    }

}