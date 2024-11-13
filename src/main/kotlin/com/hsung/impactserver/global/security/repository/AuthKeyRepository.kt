package com.hsung.impactserver.global.security.repository

import com.hsung.impactserver.global.security.data.entity.AuthKey
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AuthKeyRepository : CrudRepository<AuthKey, UUID> {
    fun findByUserId(userId: UUID): Optional<AuthKey>


    fun deleteByUserId(userId: UUID)
}