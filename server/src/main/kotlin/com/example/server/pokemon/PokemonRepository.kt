package com.example.server.pokemon

import com.example.server.entity.FavoriteRecord
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
@Transactional
interface PokemonRepository: JpaRepository<FavoriteRecord, UUID> {
    fun existsByUserIdAndPokemonName(userId: UUID, pokemonName: String): Boolean
    fun deleteByUserIdAndPokemonName(userId: UUID, pokemonName: String)
}