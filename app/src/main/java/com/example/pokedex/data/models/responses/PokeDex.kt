package com.example.pokedex.data.models.responses


import com.google.gson.annotations.SerializedName

data class PokeDex(
    val descriptions: List<Description>,
    val id: Int,
    @SerializedName("is_main_series")
    val isMainSeries: Boolean,
    val name: String,
    val names: List<Name>,
    @SerializedName("pokemon_entries")
    val pokemonEntries: List<PokemonEntry>,
    val region: Region,
    @SerializedName("version_groups")
    val versionGroups: List<VersionGroup>
) {
    data class Description(
        val description: String,
        val language: Language
    ) {
        data class Language(
            val name: String,
            val url: String
        )
    }

    data class Name(
        val language: Language,
        val name: String
    ) {
        data class Language(
            val name: String,
            val url: String
        )
    }

    data class PokemonEntry(
        @SerializedName("entry_number")
        val entryNumber: Int,
        @SerializedName("pokemon_species")
        val pokemonSpecies: PokemonSpecies
    ) {
        data class PokemonSpecies(
            val name: String,
            val url: String
        )
    }

    data class Region(
        val name: String,
        val url: String
    )

    data class VersionGroup(
        val name: String,
        val url: String
    )
}
