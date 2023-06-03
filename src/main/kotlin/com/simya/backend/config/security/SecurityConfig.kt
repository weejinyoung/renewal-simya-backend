package com.simya.backend.config.security

import com.simya.backend.config.jwt.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.CorsFilter


@Configuration
class SecurityConfig (
    private val jwtProvider: JwtProvider,
    private val corsFilter: CorsFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    protected fun config(http: HttpSecurity): SecurityFilterChain =
         http
             .csrf { csrf ->
                 csrf.disable()
                     .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter::class.java)
             }

             .sessionManagement {
                 it
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             }

             .addFilterBefore(JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
             .exceptionHandling { handling ->
                 handling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                     .accessDeniedHandler(jwtAccessDeniedHandler)
             }

             .authorizeHttpRequests {
                 it
                     .requestMatchers("/swagger-ui/**", "/api-docs/**", "/v1/**", "/api")
                     .permitAll()
                     .anyRequest()
                     .authenticated()
             }

             .build()

}