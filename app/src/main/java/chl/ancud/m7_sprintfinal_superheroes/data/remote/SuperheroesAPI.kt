package chl.ancud.m7_sprintfinal_superheroes.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroesAPI {

    @GET("superheroes/")
    suspend fun getData(): Response<List<SuperheroesListado>>

    @GET("superheroes/{id}")
    suspend fun getDataDetalle(@Path("id") id: Int): Response<SuperheroesDetalle>
}