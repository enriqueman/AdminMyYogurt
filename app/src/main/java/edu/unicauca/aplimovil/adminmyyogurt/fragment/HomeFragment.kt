package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.unicauca.aplimovil.adminmyyogurt.R
//import edu.unicauca.aplimovil.adminmyyogurt.

class HomeFragment : Fragment() {

private lateinit var binding : HomeFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding= HomeFragment.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}