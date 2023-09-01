package chl.ancud.m7_sprintfinal_superheroes.data

import androidx.lifecycle.LiveData
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDao
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDetalleEntity
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesListadoEntity
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesDetalle
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesListado
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesAPI


import retrofit2.Response

class Repositorio(private val superheroesAPI: SuperheroesAPI, private val superheroesDao: SuperheroesDao) {

    fun obtenerSuperheroesLiveData(): LiveData<List<SuperheroesListadoEntity>> = superheroesDao.selectSuperheroes()

    fun obtenerSuperheroesDetalleLiveData(id: Int): LiveData<SuperheroesDetalleEntity> = superheroesDao.selectSuperheroeDetalle(id)


    suspend fun getSuperheroes() {
        val response: Response<List<SuperheroesListado>> = superheroesAPI.getData()
        if(response.isSuccessful) {
            val resp: List<SuperheroesListado>? = response.body()
            resp?.let {
                val superheroesListado = it.map { it.transformar() }
                superheroesDao.insertSuperheroes(superheroesListado)
            }
        }
    }

    suspend fun getSuperheroesDetalle(id: Int) {
        val response: Response<SuperheroesDetalle> = superheroesAPI.getDataDetalle(id)
        if (response.isSuccessful) {
            val resp: SuperheroesDetalle? = response.body()
            resp?.let {
                val superheroesDetalleEntity = it.transformar()
                superheroesDao.insertSuperheroeDetalle(superheroesDetalleEntity)
            }
        }
    }
}