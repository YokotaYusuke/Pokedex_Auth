package com.example.server.pokemon

import com.example.server.entity.FavoriteRecord
import com.example.server.model.Favorite
import org.springframework.stereotype.Service
import java.util.UUID

interface PokemonService {
    fun getPokemonIsFavorite(userId: String, pokemonName: String): Favorite
    fun savePokemonFavorite(userId: String, pokemonName: String): String
}

@Service
class DefaultPokemonService(
    private val pokemonRepository: PokemonRepository
): PokemonService {
    override fun getPokemonIsFavorite(userId: String, pokemonName: String): Favorite {
        println("=================================================")
        val enabled = pokemonRepository.existsByUserIdAndPokemonName(
            UUID.fromString(userId),
            pokemonName
        )
        return Favorite(enabled)
    }

    override fun savePokemonFavorite(userId: String, pokemonName: String): String {
        val favoriteRecord = FavoriteRecord(UUID.fromString(userId), pokemonName)
        val favoriteExists = pokemonRepository.existsByUserIdAndPokemonName(UUID.fromString(userId), pokemonName)
        if (favoriteExists) {
            pokemonRepository.deleteByUserIdAndPokemonName(UUID.fromString(userId), pokemonName)
        } else {
            pokemonRepository.save(favoriteRecord)
        }
        return """"${favoriteRecord.pokemonName}""""
    }
}