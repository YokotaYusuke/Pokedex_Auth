package com.example.server.repository

import com.example.server.entity.UserRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserRecord, UUID> {
    fun findByUsername(name: String): UserRecord?
    fun existsByUsername(name: String): Boolean
}