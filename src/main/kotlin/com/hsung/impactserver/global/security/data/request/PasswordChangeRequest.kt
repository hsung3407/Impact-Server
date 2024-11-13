package com.hsung.impactserver.global.security.data.request

data class PasswordChangeRequest(
    val id: String,
    val password: String,
    val newPassword: String,
)