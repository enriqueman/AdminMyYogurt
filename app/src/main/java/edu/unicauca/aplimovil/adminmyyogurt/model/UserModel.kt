package edu.unicauca.aplimovil.adminmyyogurt.model

data class UserModel(
    val userId: String? = "",
    val nombre: String? = "",
    val apellido: String? = "",
    var telefono: String? = "",
    val correo: String? = "",
    val genero: String? = "",
    val ocupacion: String? = "",
    val edad: String? = ""
)