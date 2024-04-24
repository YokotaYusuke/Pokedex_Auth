package com.example.server.config

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtConfig (
    @Value("\${security.key}")
    private val jwtKey: String,
) {
    private val secretKey = SecretKeySpec(jwtKey.toByteArray(), "HmacSHA256")

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val secret = ImmutableSecret<SecurityContext>(secretKey)
        return NimbusJwtEncoder(secret)
    }
}