package com.example.pokedex.views

import com.example.pokedex.data.models.responses.FetchStatus
import com.example.pokedex.data.models.responses.PokeDexState
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.net.SocketException

@RunWith(MockitoJUnitRunner::class)
class PokeDexViewTest {

    @Spy
    private lateinit var view: SpyablePokeDexView

    @Test
    fun `pokedex fetch in progress and user sees progress bar`() {
        // Setup
        val loadingState =
            PokeDexState(fetchStatus = FetchStatus.Loading, pokemonList = listOf(), error = null)

        // Act
        view.render(loadingState)

        // Assert
        verify(view).showProgress(true)
    }

    @Test
    fun `pokedex fetch failed with an error`() {
        // Setup
        val errorMsg = SocketException().message
        val errorState =
            PokeDexState(fetchStatus = FetchStatus.Failure, pokemonList = null, error = errorMsg)
        // Act
        view.render(errorState)
        // Assert
        verify(view).showProgress(false)
        verify(view).showError(errorMsg)
    }

    @Test
    fun `pokedex fetch finished successfully and user sees pokemon list`() {
        // Setup
        val successState =
            PokeDexState(fetchStatus = FetchStatus.Success, pokemonList = listOf(), error = null)
        // Act
        view.render(successState)
        // Assert
        verify(view).showProgress(false)
        verify(view).showPokeDex(successState.pokemonList)
    }
}
