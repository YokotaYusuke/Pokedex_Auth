package com.example.server.pokemon

import com.example.server.model.Favorite
import com.example.server.toUser
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon/favorite")
class PokemonController(
    private val pokemonService: PokemonService
) {
    @GetMapping
    fun getPokemonIsFavorite(
        authentication: Authentication,
        @RequestParam name: String,
    ): Favorite {
        val id = authentication.toUser().id
        val pokemonIsFavorite = pokemonService.getPokemonIsFavorite(id, name)
        return pokemonIsFavorite
    }

    @PostMapping
    fun savePokemonFavorite(
        @RequestParam userId: String,
        @RequestParam name: String,
    ): String {
//        val id = authentication.toUser().id
        return pokemonService.savePokemonFavorite(userId, name)
    }

    @GetMapping("/bool")
    fun getBool(): Boolean {
        return true
    }
}