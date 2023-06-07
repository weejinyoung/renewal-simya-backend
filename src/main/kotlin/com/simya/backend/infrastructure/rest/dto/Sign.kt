package com.simya.backend.infrastructure.rest.dto

import com.simya.backend.domain.member.Member
import com.simya.backend.domain.member.Role
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder




data class SignUpRequestDto (
    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(
        regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
        message = "이메일은 @를 포함해야 합니다."
    )
    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    val email: String,

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
        message = "비밀번호는 최소 8자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다."
    )
    @Schema(description = "비밀번호", defaultValue = "test1234!")
    val password: String,

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    @Size(min = 2, message = "사용자 이름이 너무 짧습니다.")
    @Schema(description = "이름", defaultValue = "심야")
    val nickname: String
) {
    fun toEntity(passwordEncoder: PasswordEncoder): Member =
         Member(
             email,
             passwordEncoder.encode(password),
             Role.ROLE_MEMBER
         )

}

data class SignInRequestDto (
    @NotBlank(message = "이메일을 입력해주세요.")
    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    val email: String,

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", defaultValue = "test1234!")
    val password: String
) {
    fun toAuthentication(): UsernamePasswordAuthenticationToken =
        UsernamePasswordAuthenticationToken(email, password)
}


