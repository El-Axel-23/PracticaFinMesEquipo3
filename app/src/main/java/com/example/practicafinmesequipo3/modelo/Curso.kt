package com.example.practicafinmesequipo3.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Curso (
    @SerialName("id")
    val id: Int =0,

    @SerialName("nombre")
    val nombre: String = "",

    @SerialName("salon")
    val salon: String = "",

    @SerialName("imagen_nombre")
    val imagenNombre: String = "",

    @SerialName("descripcion")
    val descripcion: String =""
)
{
    override fun toString(): String {
        return nombre
    }
}