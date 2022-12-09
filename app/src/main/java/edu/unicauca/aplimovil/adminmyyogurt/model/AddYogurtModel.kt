package edu.unicauca.aplimovil.adminmyyogurt.model

data class AddYogurtModel(
    val yogurtid: String? = "",
    val nombre: String? = "",
    val descripcion: String? = "",
    val presentacion: String? = "",
    val precio: String? = "",
    val ph: String? = "",
    val espesor: String? = "",
    val azucar: String? = "",
    val fruta: String? = "",
    val CoverImg: String? = "",
    val categoria: String? = "",
    val yogurtImages: ArrayList<String>
)