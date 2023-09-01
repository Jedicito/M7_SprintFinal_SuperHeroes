package chl.ancud.m7_sprintfinal_superheroes.data.remote

import com.google.gson.annotations.SerializedName

class SuperheroesDetalle (
    val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    @SerializedName("año_creacion") val creacion: Int,
    val color: String,
    val traduccion: Boolean
)