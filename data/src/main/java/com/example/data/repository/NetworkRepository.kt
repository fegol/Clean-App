package com.example.data.repository

import com.example.data.network.Api
import com.example.domain.entity.ListElement
import com.example.domain.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(
    private val api: Api
) : ListRepository {
    override suspend fun getList(): List<ListElement> = withContext(Dispatchers.IO) {
        api.getData().data.elements
    }
}