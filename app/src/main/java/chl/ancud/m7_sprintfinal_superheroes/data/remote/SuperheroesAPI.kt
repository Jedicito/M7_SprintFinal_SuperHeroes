package chl.ancud.m7_sprintfinal_superheroes.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SuperheroesAPI {

    @GET("superheroes/")
    suspend fun getData(): Response<List<SuperheroesListado>>
}