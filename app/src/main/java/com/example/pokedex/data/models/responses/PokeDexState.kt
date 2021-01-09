package com.example.pokedex.data.models.responses

data class PokeDexState(
    val fetchStatus: FetchStatus,
    val pokemonList: List<PokeDex.PokemonEntry>? = null,
    val error: String? = null
)

sealed class FetchStatus {
    object Loading : FetchStatus()
    object Success : FetchStatus()
    object Failure : FetchStatus()
}
