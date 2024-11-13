package com.hsung.impactserver.global.security.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import com.hsung.impactserver.global.global.data.entity.BaseEntityCreateOnly
import java.util.*

@Entity
@Table(name = "auth")
class AuthKey(
    @Id
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    var accessToken: UUID,

    @Column(nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    val userId: UUID,
) : BaseEntityCreateOnly()