package edu.unicauca.aplimovil.adminmyyogurt.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentYogurtBinding
import edu.unicauca.aplimovil.adminmyyogurt.databinding.ImageItemBinding

class AddYogurtImageAdapter(val list: ArrayList<Uri>)
    :RecyclerView.Adapter<AddYogurtImageAdapter.AddYogurtImageViewHolder>() {
    inner class AddYogurtImageViewHolder(val binding: ImageItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddYogurtImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddYogurtImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddYogurtImageViewHolder, position: Int) {
       holder.binding.itemImg.setImageURI(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}