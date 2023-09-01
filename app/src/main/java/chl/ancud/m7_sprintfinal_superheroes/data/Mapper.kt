package chl.ancud.m7_sprintfinal_superheroes.data

import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDetalleEntity
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesListadoEntity
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesDetalle
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesListado

fun SuperheroesListado.transformar(): SuperheroesListadoEntity =
    SuperheroesListadoEntity(
        this.id,
        this.nombre,
        this.origen,
        this.imagenLink,
        this.poder,
        this.creacion
        )

fun SuperheroesDetalle.transformar(): SuperheroesDetalleEntity =
    SuperheroesDetalleEntity(
        this.id,
        this.nombre,
        this.origen,
        this.imagenLink,
        this.poder,
        this.creacion,
        this.color,
        this.traduccion
    )