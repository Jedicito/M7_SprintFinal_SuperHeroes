package chl.ancud.m7_sprintfinal_superheroes.data

import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesListadoEntity
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesDetalle
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesListado
import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {

    @Test
    fun listadoTransformar() {
        //Given
        val heroe = SuperheroesListado(1, "Nombre1", "Origen1", "Link1", "Poder1", 1111)

        //When
        val res = heroe.transformar()

        //Then
        assertEquals(heroe.id, res.id)
        assertEquals(heroe.nombre, res.nombre)
        assertEquals(heroe.origen, res.origen)
        assertEquals(heroe.imagenLink, res.imagenLink)
        assertEquals(heroe.poder, res.poder)
        assertEquals(heroe.creacion, res.creacion)
    }

    @Test
    fun detalleTransformar() {
        //Given
        val heroe = SuperheroesDetalle(1, "Nombre1", "Origen1", "Link1", "Poder1", 1111, "Color1", true)

        //When
        val res = heroe.transformar()

        //Then
        assertEquals(heroe.id, res.id)
        assertEquals(heroe.nombre, res.nombre)
        assertEquals(heroe.origen, res.origen)
        assertEquals(heroe.imagenLink, res.imagenLink)
        assertEquals(heroe.poder, res.poder)
        assertEquals(heroe.creacion, res.creacion)
        assertEquals(heroe.color, res.color)
        assertEquals(heroe.traduccion, res.traduccion)
    }
}