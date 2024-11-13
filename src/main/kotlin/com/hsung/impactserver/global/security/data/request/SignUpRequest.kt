package com.hsung.impactserver.global.security.data.request

data class SignUpRequest(
    val id: String,
    val password: String,
    val name: String,
)