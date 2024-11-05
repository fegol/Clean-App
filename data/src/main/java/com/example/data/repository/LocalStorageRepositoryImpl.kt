package com.example.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.domain.data.repository.LocalStorageRepository

class LocalStorageRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : LocalStorageRepository {
    override suspend fun markAsRead(id: Long) {
        sharedPreferences.edit {
            putBoolean("readed_$id", true)
        }
    }

    override suspend fun isMarkAsRead(id: Long): Boolean {
        return sharedPreferences.getBoolean("readed_$id", false)
    }

    override suspend fun like(id: Long, like: Boolean) {
        sharedPreferences.edit {
            putBoolean("liked_$id", like)
        }
    }

    override suspend fun isLiked(id: Long): Boolean {
        return sharedPreferences.getBoolean("liked_$id", false)
    }
}