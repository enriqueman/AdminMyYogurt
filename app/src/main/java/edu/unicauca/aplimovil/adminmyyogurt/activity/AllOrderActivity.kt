package edu.unicauca.aplimovil.adminmyyogurt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.databinding.ActivityAllOrderBinding
import edu.unicauca.aplimovil.adminmyyogurt.model.AddYogurtModel
import edu.unicauca.aplimovil.adminmyyogurt.model.CategoryModel

class AllOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllOrderBinding


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_order)
    }



}