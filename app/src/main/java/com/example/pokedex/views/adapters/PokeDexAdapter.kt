package com.example.pokedex.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokedex.data.models.responses.PokeDex
import com.example.pokedex.databinding.ItemPokemonBinding

class PokeDexAdapter(
    private val pokemonList: List<PokeDex.PokemonEntry>,
    private val listener: (pokemon: PokeDex.PokemonEntry) -> Unit
) :
    RecyclerView.Adapter<PokeDexAdapter.PokemonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val itemBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemonList[position], listener)
    }

    override fun getItemCount(): Int = pokemonList.size

    class PokemonHolder(
        private val itemBinding: ItemPokemonBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(
            pokemon: PokeDex.PokemonEntry,
            listener: (pokemon: PokeDex.PokemonEntry) -> Unit
        ) {
            // TODO: Somehow improve pokemon image loading
            itemBinding.imagePokemon.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.entryNumber}.png")
            itemBinding.textPokemon.text = pokemon.pokemonSpecies.name.capitalize()
            itemBinding.root.setOnClickListener {
                listener.invoke(pokemon)
            }
        }
    }
}
