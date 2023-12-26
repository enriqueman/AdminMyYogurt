package edu.unicauca.aplimovil.adminmyyogurt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.databinding.ItemUserDeleteLayoutBinding

import edu.unicauca.aplimovil.adminmyyogurt.model.UserModel

class UserAdapter(var contex: Context, var list: ArrayList<UserModel>,val itemClickListener: OnItemClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface OnItemClickListener {
        fun onDeleteClick(userId: String)
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = ItemUserDeleteLayoutBinding.bind(view)

        init {
            binding.DeleteUser.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val userId = list[position].userId
                    if (userId != null) {
                        itemClickListener.onDeleteClick(userId)
                    }
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(contex).inflate(R.layout.item_user_delete_layout,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    holder.binding.textViewNombre2.text = list[position].nombre
    holder.binding.textViewApellido2.text = list[position].apellido
    holder.binding.textViewNumero3.text = list[position].telefono
    }

    override fun getItemCount(): Int {
        return list.size
    }

}