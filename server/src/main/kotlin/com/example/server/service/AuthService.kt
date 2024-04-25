package com.example.server.service

import com.example.server.dto.ApiException
import com.example.server.model.LoginResponse
import org.springframework.stereotype.Service

interface AuthService {
    fun register(username: String, password: String): LoginResponse
    fun login(username: String, password: String): LoginResponse
}

@Service
class DefaultAuthService(
    private val userService: UserService,
    private val tokenService: TokenService,
    private val hashService: HashService,
): AuthService {
    override fun register(username: String, password: String): LoginResponse {
        if (userService.existByName(username)) {
            throw ApiException(400, "User name already exists")
        }

        val hashedPassword = hashService.hashBcrypt(password)
        val user = userService.saveUser(username, hashedPassword)

        val token = tokenService.createToken(user)
        return LoginResponse(token)
    }

    override fun login(username: String, password: String): LoginResponse {
        val user = userService.findByName(username) ?: throw ApiException(400, "ユーザー名またはパスワードが間違っています")

        if (!hashService.checkBcrypt(password, user.password)) {
            throw ApiException(400, "ユーザー名またはパスワードが間違っています")
        }

        val token = tokenService.createToken(user)
        return LoginResponse(token)
    }
}