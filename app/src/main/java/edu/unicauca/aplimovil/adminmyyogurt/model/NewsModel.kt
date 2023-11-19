package edu.unicauca.aplimovil.adminmyyogurt.model


data class NewsModel(
    val newsId: String? = "",
    val newsTitle: String? = "",
    val newsBody: String? = "",
    val CoverImg: String? = "",
    val yogurtCategoria: String? = "",
    val newsVera: String? = "",
    val newsDate: String? = "",
    val newslink: String? = "",
    val newsAutor: String? = "",
    //val yogurtAzucar: String? = "",
    //val yogurtFruta: String? = "",
    val yogurtImages: ArrayList<String>
)
