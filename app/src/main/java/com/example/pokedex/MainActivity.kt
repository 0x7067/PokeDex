package com.example.pokedex

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.architecture.android.viewBinding
import com.example.pokedex.data.models.responses.PokeDex
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.views.adapters.PokeDexAdapter


class MainActivity : AppCompatActivity(), PokeDexView {

    private lateinit var viewModel: MainViewModel
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchKantoPokeDex()
        viewModel.pokedexState.observe(this, Observer {
            render(it)
        })
    }

    override fun showProgress(show: Boolean) {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showPokeDex(pokemonList: List<PokeDex.PokemonEntry>?) {
        val adapter = PokeDexAdapter(pokemonList!!) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.pokemonSpecies.url)))
        }
        binding.recyclerPokedex.adapter = adapter
    }
}
