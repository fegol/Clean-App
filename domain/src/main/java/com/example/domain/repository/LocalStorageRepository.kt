package com.example.domain.repository

interface LocalStorageRepository {
    fun markAsRead(id: Long)
    fun isMarkAsRead(id: Long): Boolean
}