package com.example.pokedex

import com.example.pokedex.data.models.responses.FetchStatus
import com.example.pokedex.data.models.responses.PokeDex
import com.example.pokedex.data.models.responses.PokeDexState

interface PokeDexView {
    fun render(state: PokeDexState) {
        when (state.fetchStatus) {
            FetchStatus.Loading -> showProgress(true)
            FetchStatus.Failure -> {
                showProgress(false)
                showError(state.error)
            }
            FetchStatus.Success -> {
                showProgress(false)
                showPokeDex(state.pokemonList)
            }
        }
    }

    fun showProgress(show: Boolean)
    fun showError(error: String?)
    fun showPokeDex(pokemonList: List<PokeDex.PokemonEntry>?)
}
