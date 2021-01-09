package com.example.pokedex.views

import com.example.pokedex.PokeDexView
import com.example.pokedex.data.models.responses.PokeDex

open class SpyablePokeDexView : PokeDexView {
    override fun showProgress(show: Boolean) {}
    override fun showError(error: String?) {}
    override fun showPokeDex(pokemonList: List<PokeDex.PokemonEntry>?) {}
}
