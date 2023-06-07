package com.simya.backend.application.service

import com.simya.backend.domain.exception.member.AlreadyDeletedMemberException
import com.simya.backend.domain.exception.member.CurrentMemberNotFoundException
import com.simya.backend.domain.exception.member.DuplicatedEmailException
import com.simya.backend.domain.exception.member.MemberNotFoundException
import com.simya.backend.domain.member.Member
import com.simya.backend.infrastructure.database.mysql.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepository: MemberRepository
) {

    fun createMember(member: Member): Member {
        if (memberRepository.existsByEmail(member.email)) {
            throw DuplicatedEmailException()
        } else {
            return memberRepository.save(member)
        }
    }

    fun deleteMember(member: Member) {
        if (memberRepository.existsByEmail(member.email)) {
            memberRepository.delete(member)
        } else {
            throw AlreadyDeletedMemberException()
        }
    }

    fun updateMember() {

    }

    fun getCurrentMember() =
        try {
            getMember(SecurityContextHolder.getContext().authentication.name)
        }  catch (e: MemberNotFoundException) {
            throw CurrentMemberNotFoundException()
        }

    fun getMember(id: Long): Member =
        memberRepository.findByIdOrNull(id)
            ?:throw MemberNotFoundException()

    fun getMember(email: String): Member =
        memberRepository.findByEmail(email)
            ?:throw MemberNotFoundException()

}