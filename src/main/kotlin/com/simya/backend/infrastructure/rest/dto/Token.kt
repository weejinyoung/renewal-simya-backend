package com.simya.backend.infrastructure.rest.dto

data class TokenDto(
    val grantType: String,
    val accessToken: String,
    val refreshToken: String,
    val refreshTokenExpiresIn: Long
)

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)