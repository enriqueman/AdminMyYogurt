package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentYogurtBinding
import edu.unicauca.aplimovil.adminmyyogurt.databinding.ItemCategoryLayoutBinding


class yogurtFragment : Fragment() {
    private lateinit var binding: FragmentYogurtBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentYogurtBinding.inflate(layoutInflater)
        binding.floatingActionButton2.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_yogurtFragment_to_addYogurtFragment)
        }


        return binding.root
    }


}