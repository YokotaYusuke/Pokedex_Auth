package com.example.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "app_user")
data class UserRecord(
    @Id
    val id: UUID = UUID.randomUUID(),
    val username: String,
    val password: String,
)

