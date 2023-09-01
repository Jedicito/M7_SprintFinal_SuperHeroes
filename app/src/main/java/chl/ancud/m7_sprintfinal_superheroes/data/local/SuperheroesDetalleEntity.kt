package chl.ancud.m7_sprintfinal_superheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_superheroe_detalle")
class SuperheroesDetalleEntity (
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val creacion: Int,
    val color: String,
    val traduccion: Boolean
)