package com.example.server.config

import com.example.server.service.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.web.SecurityFilterChain

@Configuration
class OAuth2ResourceServerSecurityConfig(
    private val tokenService: TokenService,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.ignoringRequestMatchers("/api/**")
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                    .requestMatchers("/api/pokemon/favorite").permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
            }
            .oauth2ResourceServer {
                it.jwt {}
            }
            .authenticationManager {
                val bearerToken = it as BearerTokenAuthenticationToken
                val user = tokenService.parseToken(bearerToken.token) ?: throw InvalidBearerTokenException("Invalid token")
                UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
            }
        return http.build()
    }
}
