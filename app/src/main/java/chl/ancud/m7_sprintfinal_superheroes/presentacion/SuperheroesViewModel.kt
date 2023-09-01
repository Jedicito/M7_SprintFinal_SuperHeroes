package chl.ancud.m7_sprintfinal_superheroes.presentacion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import chl.ancud.m7_sprintfinal_superheroes.data.Repositorio
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDatabase
import chl.ancud.m7_sprintfinal_superheroes.data.remote.SuperheroesRetrofit
import kotlinx.coroutines.launch

class SuperheroesViewModel(application: Application): AndroidViewModel(application) {
    private val repo: Repositorio

    init {
        val api = SuperheroesRetrofit.getRetrofitSuperheroes()
        val superheroesDatabase = SuperheroesDatabase.getDatabase(application).getISuperheroesDao()
        repo = Repositorio(api, superheroesDatabase)
    }

    fun getAllSuperheroes() = viewModelScope.launch {
        repo.getSuperheroes()
    }

    fun superheroesLiveData() = repo.obtenerSuperheroesLiveData()

    fun getSuperheroesDetalle(id: Int) = viewModelScope.launch {
        repo.getSuperheroesDetalle(id)
    }

    fun superheroesDetalleLiveData(id: Int) = repo.obtenerSuperheroesDetalleLiveData(id)

}