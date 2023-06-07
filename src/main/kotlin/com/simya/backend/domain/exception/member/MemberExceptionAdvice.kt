package com.simya.backend.domain.exception.member

import com.simya.backend.infrastructure.rest.response.Response.Companion.failure
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberExceptionAdvice {

    private val log = LoggerFactory.getILoggerFactory()

    @ExceptionHandler(MemberNotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun memberNotFoundException(e: MemberNotFoundException) =
        failure("해당 사용자를 찾을 수 없습니다.")

    @ExceptionHandler(CurrentMemberNotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun currentMemberNotFoundException(e: CurrentMemberNotFoundException) =
        failure("현재 로그인 된 사용자를 찾을 수 없습니다, 다시 로그인 해주세요.")

    @ExceptionHandler(DuplicatedEmailException::class)
    @ResponseStatus(CONFLICT)
    fun memberNotFoundException(e: DuplicatedEmailException) =
        failure("이미 존재하는 이메일입니다.")

    @ExceptionHandler(AlreadyDeletedMemberException::class)
    @ResponseStatus(CONFLICT)
    fun memberNotFoundException(e: AlreadyDeletedMemberException) =
        failure("이미 회원탈퇴 처리 된 사용자입니다.")

}