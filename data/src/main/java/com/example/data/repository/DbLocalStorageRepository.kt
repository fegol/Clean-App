package com.example.data.repository

import com.example.data.db.ElementsDao
import com.example.data.db.entities.ElementEntity
import com.example.domain.data.repository.LocalStorageRepository
import timber.log.Timber

class DbLocalStorageRepository(
    private val dao: ElementsDao
) : LocalStorageRepository {
    override suspend fun markAsRead(id: Long) {
        val element = dao.elementById(id) ?: ElementEntity(id, false, false)
        val newElement = element.copy(read = true)
        dao.insertElement(newElement)
    }

    override suspend fun isMarkAsRead(id: Long): Boolean {
        return dao.elementById(id)?.read ?: false
    }

    override suspend fun like(id: Long, like: Boolean) {
        Timber.e("like $id $like")
        val element = dao.elementById(id) ?: ElementEntity(id, false, false)
        val newElement = element.copy(like = like)
        dao.insertElement(newElement)
    }

    override suspend fun isLiked(id: Long): Boolean {
        return dao.elementById(id)?.like ?: false
    }
}