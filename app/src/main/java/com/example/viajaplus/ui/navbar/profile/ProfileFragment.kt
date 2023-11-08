package com.example.viajaplus.ui.navbar.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.viajaplus.MainActivity
import com.example.viajaplus.databinding.FragmentProfileBinding
import com.example.viajaplus.models.User
import com.example.viajaplus.services.SingletonData
import com.example.viajaplus.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore

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

        //??
        val textView: TextView = binding.textNotifications
        profileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val btnCloseSesion : Button = binding.button
        btnCloseSesion.setOnClickListener{
            auth.signOut()
            SingletonData.removeUserId(requireContext())
            SingletonData.removeCurrentUser()


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



        // Notification text ese?¿
        // Tema?
        // Url
        updateView()


        val btnEditUri : Button = binding.editUriButton
        btnEditUri.setOnClickListener {
            if (binding.profileUri.text.toString().equals("null")) {
                Toast.makeText(this.context, "Introduce un valor", Toast.LENGTH_SHORT).show()
            } else {
                //Modify URL data in bbdd
                val db = Firebase.firestore

                val userId = Firebase.auth.uid
                val url = binding.profileUri.text.toString()

                val userRef = db.collection("User").whereEqualTo("userId", userId)

                userRef.get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        db.collection("User").document(document.id)
                            .update("profilePictureUrl", url)
                            .addOnSuccessListener {
                                Log.d(TAG, "Documento actualizado con éxito!")
                                Toast.makeText(
                                    this.context,
                                    "Cuenta actualizada con exito",
                                    Toast.LENGTH_SHORT
                                ).show()
                                updateView()
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error al actualizar el documento", e)
                                Toast.makeText(
                                    this.context,
                                    "Error al actualizar la cuenta",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
            }
        }

        return root
    }

    fun updateView() {
        // Obtén la instancia de Firestore
        val db = Firebase.firestore
        Log.d(TAG, "WORKS")

        // Obtén el ID del usuario actual
        val userf = Firebase.auth.currentUser
        val userId = userf?.uid
        if (userf != null) {
            Log.d(TAG, "WORKS")
            // Accede al documento del usuario en Firestore
            val docRef = db.collection("Users").whereEqualTo("userId", userId.toString())

            docRef.get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(TAG, "NOT WORKING")
                        // Crea una instancia de User con los datos del documento
                        val user = User(
                            userId = userId.toString(),
                            email = document.getString("email").toString(),
                            password = document.getString("password").toString(),
                            displayName = document.getString("displayName").toString(),
                            profilePictureUrl = document.getString("profilePictureUrl").toString()
                        )

                        Log.d(TAG, "Id: ${user.userId}")
                        Log.d(TAG, "Password: ${user.password}")
                        Log.d(TAG, "DisplayName: ${user.displayName}")
                        Log.d(TAG, "ProfilePictureUrl: ${document.getString("profilePictureUrl")}")


                        binding.profileUser.setText(user.displayName)
                        binding.profileEmail.setText(user.email)
                        binding.profileUri.setText(user.profilePictureUrl)
                        //Editar la foto
                        if(!user?.profilePictureUrl.equals("null")){
                            Glide.with(this).load(user.profilePictureUrl) .into(binding.profileImage)
                        }else{
                            Toast.makeText(this.context, "Importa una foto!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.addOnFailureListener { exception ->
                    Log.d(TAG, "FAILED")
                    Log.d(TAG, "get failed with ", exception)
                }
        } else {
            // No hay usuario autenticado
            Toast.makeText(this.context, "Cuenta no iniciada", Toast.LENGTH_SHORT).show()
            // Redirige al usuario a la actividad de inicio de sesión
            val intent = Intent(this.context, LoginActivity::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "WORKS -- ${userId}")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}