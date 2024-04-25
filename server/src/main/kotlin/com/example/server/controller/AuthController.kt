package com.example.server.controller

import com.example.server.model.AuthBody
import com.example.server.model.LoginResponse
import com.example.server.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody body: AuthBody): LoginResponse {
        val token = authService.register(body.username, body.password)
        return token
    }

    @PostMapping("/login")
    fun login(@RequestBody body: AuthBody): LoginResponse {
        return authService.login(body.username, body.password)
    }
}