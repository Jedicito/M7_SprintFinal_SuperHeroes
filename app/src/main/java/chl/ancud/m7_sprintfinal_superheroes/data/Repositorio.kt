package chl.ancud.m7_sprintfinal_superheroes.data

import androidx.lifecycle.LiveData
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDao
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesListadoEntity
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesAPI
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesListado
import retrofit2.Response

class Repositorio(private val superheroesAPI: SuperheroesAPI, private val superheroesDao: SuperheroesDao) {

    fun obtenerSuperheroesLiveData(): LiveData<List<SuperheroesListadoEntity>> = superheroesDao.selectSuperheroes()


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
}