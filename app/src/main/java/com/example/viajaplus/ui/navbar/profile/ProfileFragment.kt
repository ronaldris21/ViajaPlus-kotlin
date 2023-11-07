package com.example.viajaplus.ui.navbar.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viajaplus.MainActivity
import com.example.viajaplus.databinding.FragmentProfileBinding
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //Instancia FirebaseAuth
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth

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
            auth.signOut()
            SingletonData.removeUserId(requireContext())


            requireActivity().finish()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            this@ProfileFragment.startActivity(intent)

        }

        val btnEditUri : Button = binding.editUriButton
        btnEditUri.setOnClickListener{
            //SingletonData.removeUserId(requireContext())
            //Set image uri¿?
        }
        //TODO: cambiar tema por ejemplo
        //TODO: Documentaicón 2 puntos
        // vistas obligatorias 3.5
        // db 1.5
        // no errores 1 puntos, valudaciones
        //Originalidad diseño
        //Puntos extras



        // Notification text ese?¿
        // Tema?
        // Url -> modificar singleton o usar bbdd




        //Si el usuario existe en firebase, recoje los valores
        val user = Firebase.auth.currentUser
        user?.let {
                // Name, email address, and profile photo Url
                val name = it.displayName
                val email = it.email
                val photoUrl = it.photoUrl

            //Si el usuario tiene la sesión iniciada en la aplicación(Singleton) se mostrarán los valores
            this.context?.let {
                if(SingletonData.retrieveUserId(it.applicationContext)!=null){
                    binding.profileUser.setText(name);
                    binding.profileEmail.setText(email);
                    binding.profileImage.setImageURI(photoUrl)
                }
            }
        }?: run {
            // This block will run if user is null (i.e., there is no authenticated user)
            Toast.makeText(this.context?.applicationContext ?: null, "El usuario no ha iniciado sesión", Toast.LENGTH_SHORT)
                .show()
        }





        return root
    }

    /*
    *  val user = Firebase.auth.currentUser
            user?.let {
                // Name, email address, and profile photo Url
                val name = it.displayName
                val email = it.email
                val photoUrl = it.photoUrl

                // Check if user's email is verified
                val emailVerified = it.isEmailVerified

                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getIdToken() instead.
                val uid = it.uid
            }
    * */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}