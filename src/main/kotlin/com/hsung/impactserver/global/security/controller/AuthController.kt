package com.hsung.impactserver.global.security.controller

import jakarta.transaction.Transactional
import com.hsung.impactserver.global.security.component.TokenProvider
import com.hsung.impactserver.global.security.data.dto.TokenResponse
import com.hsung.impactserver.global.security.data.entity.User
import com.hsung.impactserver.global.security.data.request.IdChangeRequest
import com.hsung.impactserver.global.security.data.request.PasswordChangeRequest
import com.hsung.impactserver.global.security.data.request.SignInRequest
import com.hsung.impactserver.global.security.data.request.SignUpRequest
import com.hsung.impactserver.global.security.exception.LoginFailedException
import com.hsung.impactserver.global.security.exception.UserIdAlreadyExistsException
import com.hsung.impactserver.global.security.exception.UserNotFoundException
import com.hsung.impactserver.global.security.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
) {
    @PostMapping("/sign-in")
    fun signIn(@RequestBody req: SignInRequest): TokenResponse {
        val user = userRepository.findByLoginId(req.id).getOrNull() ?: throw LoginFailedException()

        if (passwordEncoder.matches(req.password, user.password)) {
            return TokenResponse.new(tokenProvider, user.id)
        }
        throw LoginFailedException()
    }

    @PutMapping("/id")
    @Transactional
    fun changeId(
        @RequestBody req: IdChangeRequest
    ) {
        val userData = userRepository.findByLoginId(req.id).getOrNull() ?: throw UserNotFoundException()
        if (userRepository.existsByLoginId(req.newId)) throw UserIdAlreadyExistsException()
        if (!passwordEncoder.matches(req.password, userData.password)) throw LoginFailedException()
        userData.loginId = req.id
    }

    @PutMapping("/password")
    @Transactional
    fun changePassword(
        @RequestBody req: PasswordChangeRequest
    ) {
        val userData = userRepository.findByLoginId(req.id).orElse(null) ?: throw UserNotFoundException()
        if (!passwordEncoder.matches(req.password, userData.password)) throw LoginFailedException()
        userData.password = passwordEncoder.encode(req.newPassword)
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody req: SignUpRequest) {
        if (userRepository.existsByLoginId(req.id)) throw UserIdAlreadyExistsException()
        val user = userRepository.save(
            User(
                req.name,
                req.id,
                passwordEncoder.encode(req.password)
            )
        )
    }
}
