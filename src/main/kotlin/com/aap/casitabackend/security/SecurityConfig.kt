package com.aap.casitabackend.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authz ->
            authz
                .requestMatchers("/v3/api-docs").permitAll()
                .requestMatchers("/api/public").permitAll()
                .requestMatchers("/api/private").authenticated()
                .requestMatchers("/api/admin").hasAuthority("SCOPE_read:ADMIN")
        }
            .cors { }
            .oauth2ResourceServer { resourceServer -> resourceServer.jwt {} }
        return http.build()
    }
}