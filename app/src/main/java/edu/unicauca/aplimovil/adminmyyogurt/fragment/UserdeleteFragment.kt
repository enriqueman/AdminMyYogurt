package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import edu.unicauca.aplimovil.adminmyyogurt.adapter.UserAdapter
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentDeleteUserBinding

import edu.unicauca.aplimovil.adminmyyogurt.model.UserModel

/**
 * A fragment representing a list of Items.
 */
class userdeleteFragment : Fragment(),  UserAdapter.OnItemClickListener{
    private lateinit var binding: FragmentDeleteUserBinding

    private var columnCount = 2



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View?{
       binding = FragmentDeleteUserBinding.inflate(layoutInflater)
        getData()


        return binding.root
    }

    private fun getData() {
        val list= ArrayList<UserModel>()
        Firebase.firestore.collection("usuarios")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data= UserModel(
                        userId = doc.id,
                        nombre = doc["nombre"]?.toString(),
                        apellido = doc["apellido"]?.toString(),
                        telefono = doc["telefono"]?.toString(),
                        correo = doc["correo"]?.toString(),
                        genero = doc["genero"]?.toString(),
                        ocupacion = doc["ocupacion"]?.toString(),
                        edad = doc["edad"]?.toString()
                    )
                    list.add(data!!)
                }
                binding.UserDeleteRecycler.adapter= UserAdapter(requireContext(),list,this)

            }

    }

    override fun onDeleteClick(userId: String) {
        deleteObjet(userId)
    }

    private fun deleteObjet(userId: String){
        Firebase.firestore.collection("usuarios").document(userId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
                // Aquí puedes actualizar tu lista de usuarios después de la eliminación
                getData()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting document", e)
            }
    }
}

