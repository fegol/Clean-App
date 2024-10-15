package com.example.domain.usecase

import android.content.res.Resources.NotFoundException
import com.example.domain.entity.ListElement
import com.example.domain.repository.CacheRepository
import com.example.domain.repository.ListRepository

class ElementByIdUseCase(
    private val repository: ListRepository,
    private val cacheRepository: CacheRepository
) : UseCase<Long, ListElement> {
    override suspend fun execute(data: Long): ListElement {
        val cached = cacheRepository.getCache<List<ListElement>>("getList")
        if (cached != null) {
            return cached.find { it.id == data } ?: throw NotFoundException()
        }
        return repository.getElement(data)
    }
}
