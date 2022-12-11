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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import edu.unicauca.aplimovil.adminmyyogurt.R
import edu.unicauca.aplimovil.adminmyyogurt.adapter.AddYogurtImageAdapter
import edu.unicauca.aplimovil.adminmyyogurt.databinding.FragmentAddYogurtBinding
import edu.unicauca.aplimovil.adminmyyogurt.model.AddYogurtModel
import edu.unicauca.aplimovil.adminmyyogurt.model.CategoryModel
import java.util.*
import kotlin.collections.ArrayList


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

        }// comentario para que lo suba kather
        else if(binding.yogurtDescripcionEdt.text.toString().isEmpty()){
            binding.yogurtDescripcionEdt.requestFocus()
            binding.yogurtDescripcionEdt.error="Empty"
        }
        else if (binding.yogurtPresentacionEdt.text.toString().isEmpty()){
            binding.yogurtPresentacionEdt.requestFocus()
            binding.yogurtPresentacionEdt.error="Empty"
        }
        else if(binding.yogurtPresentacionEdt.text.toString().isEmpty()){
            binding.yogurtPresentacionEdt.requestFocus()
            binding.yogurtPresentacionEdt.error="Empty"
        }
        else if(binding.yogurtPrecioEdt.text.toString().isEmpty()){
            binding.yogurtPrecioEdt.requestFocus()
            binding.yogurtPrecioEdt.error="Empty"
        }
        else if(binding.yogurtPhEdt.text.toString().isEmpty()){
            binding.yogurtPhEdt.requestFocus()
            binding.yogurtPhEdt.error="Empty"
        }
        else if(binding.yogurtEspesorEdt.text.toString().isEmpty()){
            binding.yogurtEspesorEdt.requestFocus()
            binding.yogurtEspesorEdt.error="Empty"
        }
        else if(binding.yogurtAzucarEdt.text.toString().isEmpty()){
            binding.yogurtAzucarEdt.requestFocus()
            binding.yogurtAzucarEdt.error="Empty"
        }
        else if(binding.yogurtFrutaEdt.text.toString().isEmpty()){
            binding.yogurtFrutaEdt.requestFocus()
            binding.yogurtFrutaEdt.error="Empty"
        }
        else if (coverImage==null){
            Toast.makeText(requireContext(), "Please select cover image", Toast.LENGTH_SHORT).show()
        }
        else if (list.size<1){
            Toast.makeText(requireContext(), "Please select product image", Toast.LENGTH_SHORT).show()
        }else{
            uploadImage()
        }


        // hasta aqui
    }

    //para que lo añada kather
    private fun uploadImage() {
        dialog.show()

        val fileName = UUID.randomUUID().toString()+".jpg"

        val refStorage = FirebaseStorage.getInstance().reference.child("yogures/$fileName")
        refStorage.putFile(coverImage!!)
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener {
                        image->coverImgUrl=image.toString()
                    uploadYogurtImage()
                }
            }
            .addOnFailureListener{
                dialog.dismiss()
                Toast.makeText(requireContext(), "Something went wrong with storage", Toast.LENGTH_SHORT).show()
            }
    }
    //hasta aqui
 // para que lo añada kather
    private var i=0
    private fun uploadYogurtImage() {

        dialog.show()

        val fileName = UUID.randomUUID().toString()+".jpg"

        val refStorage = FirebaseStorage.getInstance().reference.child("yogures/$fileName")
        refStorage.putFile(list[i])
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener {image->
                    listImages.add(image!!.toString())
                    if (list.size==listImages.size) {
                        storeData()
                    }else{
                        i +=1
                        uploadYogurtImage()

                    }
                }
            }
            .addOnFailureListener{
                dialog.dismiss()
                Toast.makeText(requireContext(), "Something went wrong with storage", Toast.LENGTH_SHORT).show()
            }


    }

    private fun storeData() {
        val db = Firebase.firestore.collection("yogures")
        val key= db.document().id

        val data =AddYogurtModel(

            binding.yogurtNameEdt.text.toString(),
            binding.yogurtDescripcionEdt.text.toString(),
            coverImgUrl.toString(),
            categoryList[binding.yogurtCategoryDropdown.selectedItemPosition],
            key,
            binding.yogurtPresentacionEdt.text.toString(),
            binding.yogurtPrecioEdt.text.toString(),
            binding.yogurtPhEdt.text.toString(),
            binding.yogurtEspesorEdt.text.toString(),
            binding.yogurtAzucarEdt.text.toString(),
            binding.yogurtFrutaEdt.text.toString(),
            listImages


        )
        db.document(key).set(data).addOnSuccessListener {
            dialog.dismiss()
            Toast.makeText(requireContext(), "Product Added", Toast.LENGTH_SHORT).show()
            binding.yogurtNameEdt.text = null

        }
            .addOnFailureListener {
                dialog.dismiss()
                Toast.makeText(requireContext(), "Something went wrong, ups :(", Toast.LENGTH_SHORT).show()

            }
    }

    //fin

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