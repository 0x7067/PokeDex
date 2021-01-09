package com.example.pokedex.data

import com.example.pokedex.data.models.PokeDex
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("api/v2/pokedex/{regionName}")
    suspend fun getPokeDexFor(@Path("regionName") regionName: String): Response<PokeDex>
}
