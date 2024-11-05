package com.example.domain.data.repository

interface LocalStorageRepository {
    suspend fun markAsRead(id: Long)
    suspend fun isMarkAsRead(id: Long): Boolean

    suspend fun like(id: Long, like: Boolean)
    suspend fun isLiked(id: Long): Boolean
}