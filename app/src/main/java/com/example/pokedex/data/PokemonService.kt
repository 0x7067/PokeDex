package com.example.pokedex.data

import com.example.pokedex.BuildConfig
import com.example.pokedex.data.models.responses.PokeDex
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonService {

    var api : PokemonAPI

    init {
        val okHttpClient = okHttpClient()

        api = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokemonAPI::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    suspend fun getPokeDexFor(region: String): Response<PokeDex> = api.getPokeDexFor(region)
}
