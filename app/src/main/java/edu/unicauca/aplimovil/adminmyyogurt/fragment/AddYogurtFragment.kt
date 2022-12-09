package edu.unicauca.aplimovil.adminmyyogurt.fragment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.adapter.AddYogurtImageAdapter
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentAddYogurtBinding
import edu.unicauca.aplimovil.adminmyyogurt.model.CategoryModel


class AddYogurtFragment : Fragment() {
    private lateinit var binding: FragmentAddYogurtBinding
    private lateinit var list: ArrayList<Uri>
    private lateinit var listImages: ArrayList<String>
    private lateinit var adapter: AddYogurtImageAdapter
    private var coverImage: Uri?= null
    private lateinit var dialog: Dialog
    private var coverImgUrl : String?= ""
    private lateinit var categoryList: ArrayList<String>

    private var launchGalleryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if ( it.resultCode == Activity.RESULT_OK){
            coverImage = it.data!!.data
            binding.yogurtCoverImg.setImageURI(coverImage)
            binding.yogurtCoverImg.visibility = VISIBLE
        }
    }

    private var launchYogurtActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if ( it.resultCode == Activity.RESULT_OK){
            val imageUrl = it.data!!.data
            list.add(imageUrl!!)
            adapter.notifyDataSetChanged()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddYogurtBinding.inflate(layoutInflater)

        list = ArrayList()
        listImages= ArrayList()
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.progress_layaut)
        dialog.setCancelable(false)

        binding.selectCoverBotton.setOnClickListener{
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchGalleryActivity.launch(intent)
        }

        binding.yogurtImgBotton.setOnClickListener{
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchYogurtActivity.launch(intent)
        }



        setYogurtCategory()

        adapter = AddYogurtImageAdapter(list)
        binding.yogurtImgRecyclerView.adapter =adapter

        binding.submitYogurtBtn.setOnClickListener {
            validateData()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun validateData() {
        if (binding.yogurtNameEdt.text.toString().isEmpty()){
            binding.yogurtNameEdt.requestFocus()
            binding.yogurtNameEdt.error="Empty"

        }//else (binding.yo)
    }

    private fun setYogurtCategory(){
        categoryList = ArrayList()
        Firebase.firestore.collection("categories").get().addOnSuccessListener {
            categoryList.clear()
            for (doc in it.documents){
                val data = doc.toObject(CategoryModel::class.java)
                categoryList.add(data!!.cate!!)

            }
            categoryList.add(index = 0,"Select Category")

            val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,categoryList)
            binding.yogurtCategoryDropdown.adapter= arrayAdapter
        }

    }


}