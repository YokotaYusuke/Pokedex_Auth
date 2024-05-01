package com.example.server.service

import com.example.server.entity.UserRecord
import com.example.server.repository.UserRepository
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

@Service
class TokenService(
    private val jwtEncoder: JwtEncoder,
    private val jwtDecoder: JwtDecoder,
    private val userRepository: UserRepository,
) {
    fun createToken(user: UserRecord): String {
        val jwsHeader = JwsHeader.with {"HS256"}.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("id", user.id.toString())
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): UserRecord? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = UUID.fromString(jwt.id)
            userRepository.findById(userId).get()
        } catch (e: Exception) {
            null
        }
    }
}