package com.example.server.service

import com.example.server.entity.UserRecord
import com.example.server.repository.UserRepository
import org.springframework.stereotype.Service

interface UserService {
    fun existByName(name: String): Boolean
    fun saveUser(username: String, password: String): UserRecord
    fun findByName(name: String): UserRecord?
}

@Service
class DefaultUserService(
    private val userRepository: UserRepository
) : UserService {
    override fun existByName(name: String): Boolean {
        return userRepository.existsByUsername(name)
    }

    override fun saveUser(username: String, password: String): UserRecord {
        val user = userRepository.save(UserRecord(username = username, password = password))
        return user
    }

    override fun findByName(name: String): UserRecord? {
        return userRepository.findByUsername(name)
    }
}