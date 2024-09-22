package com.example.data.repository

import com.example.domain.entity.ListButton
import com.example.domain.entity.ListElement
import com.example.domain.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ListRepositoryImpl : ListRepository {
    override suspend fun getList(): List<ListElement> = withContext(Dispatchers.IO) {
        delay(5_000)
        return@withContext listOf(
            ListElement(
                image = "https://avatars.mds.yandex.net/i?id=df6f0a9b59a9738610c34882daea14963b51056a-8249766-images-thumbs&n=13",
                title = "title",
                subtitle = "test",
                button = ListButton(
                    title = "test"
                )
            ),
            ListElement(
                image = "https://avatars.mds.yandex.net/i?id=df6f0a9b59a9738610c34882daea14963b51056a-8249766-images-thumbs&n=13",
                title = "title",
                subtitle = "test",
                button = ListButton(
                    title = "test"
                )
            ),
            ListElement(
                image = "https://avatars.mds.yandex.net/i?id=df6f0a9b59a9738610c34882daea14963b51056a-8249766-images-thumbs&n=13",
                title = "title",
                subtitle = "test",
                button = ListButton(
                    title = "test"
                )
            ),
            ListElement(
                image = "https://avatars.mds.yandex.net/i?id=df6f0a9b59a9738610c34882daea14963b51056a-8249766-images-thumbs&n=13",
                title = "title",
                subtitle = "test",
                button = ListButton(
                    title = "test"
                )
            )
        )
    }
}