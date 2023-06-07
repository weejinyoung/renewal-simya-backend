package com.simya.backend.application.usecase

import com.simya.backend.application.service.MemberService
import com.simya.backend.config.jwt.JwtProvider
import com.simya.backend.infrastructure.database.redis.RedisService
import com.simya.backend.infrastructure.rest.dto.SignInRequestDto
import com.simya.backend.infrastructure.rest.dto.SignUpRequestDto
import com.simya.backend.infrastructure.rest.dto.TokenDto
import com.simya.backend.infrastructure.rest.dto.TokenResponseDto
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration


@Service
class AuthUseCase (
    private val memberService: MemberService,
    private val redisService: RedisService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtProvider: JwtProvider
){

    @Transactional
    fun signUp(request: SignUpRequestDto) {
        memberService.createMember(request.toEntity())
    }


    @Transactional
    fun signIn(request: SignInRequestDto): TokenResponseDto {
        val authentication: Authentication =
            authenticationManagerBuilder.getObject().authenticate(request.toAuthentication())
        val tokenDto: TokenDto = authorize(authentication)
        return TokenResponseDto(tokenDto.accessToken, tokenDto.refreshToken)

    }

    @Transactional
    fun withdrawal() {

    }

    private fun authorize(authentication: Authentication): TokenDto {
        val tokenDto: TokenDto = jwtProvider.createTokenDto(authentication)
        redisService.setValues(
            "RT: " + authentication.name,
            tokenDto.refreshToken,
            Duration.ofMillis(tokenDto.refreshTokenExpiresIn)
        )
        return tokenDto
    }


}