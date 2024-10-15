package com.example.domain.repository

interface CacheRepository {
    suspend fun <T> getAndSave(
        force: Boolean = false,
        key: String,
        remote: suspend () -> T
    ): T

    suspend fun <T> getCache(key: String): T?
}