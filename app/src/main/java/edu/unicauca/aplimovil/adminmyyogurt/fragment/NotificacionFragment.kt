package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.app.Activity
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat
import androidx.databinding.adapters.TextViewBindingAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentNotificacionBinding
import edu.unicauca.aplimovil.adminmyyogurt.model.AddYogurtModel
import edu.unicauca.aplimovil.adminmyyogurt.model.UserModel


class NotificacionFragment : Fragment() {
    private lateinit var binding: FragmentNotificacionBinding
    private lateinit var newsList: ArrayList<String>
    private lateinit var userList: ArrayList<String>
    private lateinit var userList2: ArrayList<String>
    private lateinit var listusermodel: UserModel
    private lateinit var dialog: Dialog
    private var telefo : String?= ""
    private var mens : String?= ""




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotificacionBinding.inflate(layoutInflater)
        dialog = Dialog(requireContext())

        setYogurtCategory()
        setUserTelefono()
        sendmsm()

        return binding.root
    }


    private fun setYogurtCategory() {
        newsList = ArrayList()
        Firebase.firestore.collection("noticias").get().addOnSuccessListener {
            newsList.clear()
            for (doc in it.documents) {
                print("Holidd")

                val data = AddYogurtModel(
                    newsId = doc.id,
                    newsTitle = doc["newsTitle"] as String?,
                    newsBody = doc["newsBody"] as String?,
                    CoverImg = doc["CoverImg"] as String?,
                    yogurtCategoria = doc["yogurtCategoria"] as String?,
                    newsVera = doc["newsVera"] as String?,
                    newsDate = doc["newsDate"] as String?,
                    newslink = doc["newslink"] as String?,
                    newsAutor = doc["newsAutor"] as String?,
                    yogurtImages = doc["yogurtImages"] as ArrayList<String>,
                )

                newsList.add(data!!.newsTitle!!)

                print(data)

            }
            newsList.add(index = 0, "Seleccionar noticia")

            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, newsList)
            binding.newsBodyDropdown.adapter = arrayAdapter
            binding.newsBodyDropdown.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {

                        if (position != 0) {
                            val selectedItem = parent.getItemAtPosition(position).toString()
                            Firebase.firestore.collection("noticias").whereEqualTo("newsTitle", selectedItem).get().addOnSuccessListener {
                                // Obtener el teléfono del usuario
                                val body = it.documents.first().get("newsBody") as? String ?: ""
                                binding.bodyNewsEditText.setText(body)

                            }
                        }


                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

        }
    }


    private fun setUserTelefono(){
        userList = ArrayList()
        Firebase.firestore.collection("usuarios").get().addOnSuccessListener {
            userList.clear()
            for (doc in it.documents) {
                val data = UserModel(
                    userId = doc.id,
                    nombre = doc["nombre"] as String?,
                    telefono = doc["telefono"].toString(),
                )
                //userList.add(data.userId.toString())
                //   userList.add(data?.telefono?:"Telefono")
                userList.add(data?.nombre ?: "nombre")


            }


            userList.add(index = 0, "Seleccionar Usuario")

            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, userList)
            binding.userNameDropdown.adapter = arrayAdapter
            //binding.userNumberEditText

            binding.userNameDropdown.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {

                        if (position != 0) {
                            val selectedItem = parent.getItemAtPosition(position).toString()
                            Firebase.firestore.collection("usuarios").whereEqualTo("nombre", selectedItem).get().addOnSuccessListener {
                                // Obtener el teléfono del usuario
                                val telefono = it.documents.first().get("telefono") as? String ?: ""
                                binding.userNumberEditText.setText(telefono)

                            }
                        }


                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }


                }

        }


    }



    private fun sendmsm(){

        binding.idBtnSendMessage.setOnClickListener {

            telefo = binding.userNumberEditText.text.toString()
            mens = binding.bodyNewsEditText.text.toString()

            try {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(telefo, null, mens, null, null)

            }catch (e: Exception) {

                // on catch block we are displaying toast message for error.
                Toast.makeText(
                    requireContext(),
                    e.message.toString(),
                    Toast.LENGTH_LONG
                )
                    .show()
            }


        }

    }


    }




