package com.example.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonService
import com.example.pokedex.data.models.responses.FetchStatus
import com.example.pokedex.data.models.responses.PokeDexState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val service = PokemonService()
    private val _pokedexState = MutableLiveData<PokeDexState>()

    val pokedexState: LiveData<PokeDexState>
        get() = _pokedexState

    fun fetchKantoPokeDex() {
        _pokedexState.value =
            PokeDexState(fetchStatus = FetchStatus.Loading, pokemonList = null, error = null)

        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getPokeDexFor("kanto")

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _pokedexState.value = PokeDexState(
                        fetchStatus = FetchStatus.Success,
                        pokemonList = res.body()!!.pokemonEntries,
                        error = null
                    )
                }
            } else {
                withContext(Dispatchers.Main) {
                    _pokedexState.value = PokeDexState(
                        fetchStatus = FetchStatus.Failure,
                        pokemonList = null,
                        error = "Um erro aconteceu"
                    )
                }
            }
        }
    }
}
