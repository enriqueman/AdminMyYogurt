package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.activity.AllOrderActivity
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentHomeBinding

//import edu.unicauca.aplimovil.adminmyyogurt.BuildConfig.

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment3_to_categoryFragment)
            }
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment3_to_yogurtFragment)
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment3_to_sliderFragment)
        }
        binding.button4.setOnClickListener {
           startActivity(Intent(requireContext(),AllOrderActivity::class.java))
        }
        return binding.root
    }

}