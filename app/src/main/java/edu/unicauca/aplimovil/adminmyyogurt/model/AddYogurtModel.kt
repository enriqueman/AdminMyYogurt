package edu.unicauca.aplimovil.adminmyyogurt.model

data class AddYogurtModel(
    val yogurtName: String? = "",
    val yogurtDescripcion: String? = "",
    val CoverImg: String? = "",
    val yogurtCategoria: String? = "",
    val yogurtId: String? = "",
    val yogurtPresentacion: String? = "",
    val yogurtPrecio: String? = "",
    val yogurtPh: String? = "",
    val yogurtEspesor: String? = "",
    val yogurtAzucar: String? = "",
    val yogurtFruta: String? = "",
    val yogurtImages: ArrayList<String>

)