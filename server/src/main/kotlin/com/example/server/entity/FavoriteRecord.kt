package com.example.server.entity

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity
@IdClass(FavoritePK::class)
@Table(name = "favorite")
data class FavoriteRecord(
    @Id
    val userId: UUID,

    @Id
    val pokemonName: String,
)

@Embeddable
class FavoritePK: Serializable {
    private val userId: UUID = UUID.randomUUID()
    private val pokemonName: String = ""
}
