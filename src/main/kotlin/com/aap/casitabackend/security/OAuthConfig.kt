package com.aap.casitabackend.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class OAuthConfig(
    @Value("\${okta.oauth2.issuer}")
    private val issuer: String
) {
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests { authz ->
            authz
                .requestMatchers("/login")
                .permitAll()
                .anyRequest().authenticated()
        }
            .oauth2Login(Customizer.withDefaults())
            .build()
    }

}