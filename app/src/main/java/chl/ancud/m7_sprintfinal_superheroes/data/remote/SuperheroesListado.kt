package chl.ancud.m7_sprintfinal_superheroes.data.remote

import com.google.gson.annotations.SerializedName

data class SuperheroesListado(
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @SerializedName("Año_creacion") val creacion: Int
)
