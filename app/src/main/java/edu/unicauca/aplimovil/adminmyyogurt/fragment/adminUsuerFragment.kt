package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentAdminUsuerBinding


class adminUsuerFragment : Fragment() {

    private lateinit var binding: FragmentAdminUsuerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAdminUsuerBinding.inflate(layoutInflater)
        binding.btnAddUser.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_adminUsuerFragment_to_userFragment)
        }
        binding.btnDeleteUser.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_adminUsuerFragment_to_userdeleteFragment)
        }

        return binding.root
    }


}




