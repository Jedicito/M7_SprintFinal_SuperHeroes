package chl.ancud.m7_sprintfinal_superheroes.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroesRetrofit {

    companion object {
        private const val URL_BASE = "https://y-mariocanedo.vercel.app/"

        fun getRetrofitSuperheroes(): SuperheroesAPI {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(SuperheroesAPI::class.java)
        }
    }

}