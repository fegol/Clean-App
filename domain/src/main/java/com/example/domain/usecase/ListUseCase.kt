package com.example.domain.usecase

import com.example.domain.entity.ListElement
import com.example.domain.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ListUseCase(
    private val repository: ListRepository
): UseCase<Unit, List<ListElement>> {
    override suspend fun execute(data: Unit): List<ListElement> = withContext(Dispatchers.Default) {
        delay(500)
        return@withContext repository.getList()
    }
}