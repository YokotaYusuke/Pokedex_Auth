package com.example.server

import com.example.server.model.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}