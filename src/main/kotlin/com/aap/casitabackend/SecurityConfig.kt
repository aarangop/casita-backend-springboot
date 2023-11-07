package com.aap.casitabackend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    private val pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun authentication(): UserDetailsService {
        val admin = User.builder()
            .username("admin")
            .password(pwEncoder.encode("ppasword"))
            .roles("USER", "ADMIN")
            .build()
        val muergano = User.builder()
            .username("muergano")
            .password(pwEncoder.encode("ppasword"))
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(admin, muergano)
    }

    @Throws(Exception::class)
    @Bean
     fun filterChain(http: HttpSecurity): SecurityFilterChain {
         http.authorizeHttpRequests {authz -> authz
             .requestMatchers("/api/users/**").hasRole("ADMIN")
             .anyRequest().authenticated()
         }
             .formLogin {formLogin -> formLogin
                 .loginPage("/login")
             }
             .httpBasic{}
        return http.build()
    }
}