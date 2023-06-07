package com.simya.backend.infrastructure.rest.controller

import com.simya.backend.application.usecase.AuthUseCase
import com.simya.backend.infrastructure.rest.dto.SignInRequestDto
import com.simya.backend.infrastructure.rest.dto.SignUpRequestDto
import com.simya.backend.infrastructure.rest.response.Response
import com.simya.backend.infrastructure.rest.response.Response.Companion.success
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*


@Tag(name = "Member", description = "Member API Document")
@RestController
@RequestMapping("/v1/members")class MemberController(
    private val authUseCase: AuthUseCase
) {

    @Operation(summary = "Sign up API")
    @ResponseStatus(CREATED)
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequestDto): Response {
        authUseCase.signUp(request)
        return success(CREATED.reasonPhrase)
    }

    @Operation(summary = "Sign in API")
    @ResponseStatus(OK)
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequestDto): Response =
        success(OK.reasonPhrase, authUseCase.signIn(request))

}