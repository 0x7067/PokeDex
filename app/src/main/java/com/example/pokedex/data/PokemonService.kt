package com.example.pokedex.data

import com.example.pokedex.BuildConfig
import com.example.pokedex.data.models.PokeDex
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonService {
    private val api: PokemonAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonAPI::class.java)

    suspend fun getPokeDexFor(region: String): Response<PokeDex> = api.getPokeDexFor(region)
}
