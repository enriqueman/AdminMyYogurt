package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentUserBinding
import edu.unicauca.aplimovil.adminmyyogurt.model.UserModel

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    //private lateinit var dialog: Dialog



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(layoutInflater)
        //dialog.setContentView(R.layout.progress_layaut)

        binding.submitUserBtn.setOnClickListener{
            validateData()
        }



        return binding.root
    }

    private fun validateData() {
        if(binding.userNameEdt.toString().isEmpty()){
            binding.userNameEdt.requestFocus()
            binding.userNameEdt.error="Es requerido"
        } else if(binding.userApellidoEdt.text.toString().isEmpty()){
            binding.userApellidoEdt.requestFocus()
            binding.userApellidoEdt.error="Es requerido"
        }else if(binding.userTelefonoEdt.text.toString().isEmpty()){
            binding.userTelefonoEdt.requestFocus()
            binding.userTelefonoEdt.error="Es requerido"
        }else if(binding.userCorreoEdt.text.toString().isEmpty()){
            binding.userCorreoEdt.requestFocus()
            binding.userCorreoEdt.error="Es requerido"
        }else if(binding.userGeneroEdt.text.toString().isEmpty()){
            binding.userGeneroEdt.requestFocus()
            binding.userGeneroEdt.error="Es requerido"
        }else if(binding.userOcupacionEdt.text.toString().isEmpty()){
            binding.userOcupacionEdt.requestFocus()
            binding.userOcupacionEdt.error="Es requerido"
        }else if(binding.userEdadEdt.text.toString().isEmpty()){
            binding.userEdadEdt.requestFocus()
            binding.userEdadEdt.error="Es requerido"
        }else{
            storeData()
        }

    }

    private fun storeData() {
        val db = Firebase.firestore.collection("usuarios")
        val key= db.document().id

        val data =UserModel(
            key,
            binding.userNameEdt.text.toString(),
            binding.userApellidoEdt.text.toString(),
            binding.userTelefonoEdt.text.toString(),
            binding.userCorreoEdt.text.toString(),
            binding.userGeneroEdt.text.toString(),
            binding.userOcupacionEdt.text.toString(),
            binding.userEdadEdt.text.toString(),

        )

        db.document(key).set(data).addOnSuccessListener {
           // dialog.dismiss()
            Toast.makeText(requireContext(), "User Added", Toast.LENGTH_SHORT).show()
            binding.userNameEdt.text = null
        }
            .addOnFailureListener {
             //   dialog.dismiss()
                Toast.makeText(requireContext(), "Something went wrong, ups :(", Toast.LENGTH_SHORT).show()

            }


    }
}