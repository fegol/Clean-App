package com.example.data.repository

import com.example.domain.data.entity.ListButton
import com.example.domain.data.entity.ListElement
import com.example.domain.data.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ListRepositoryImpl : ListRepository {

    private val list = listOf(
        ListElement(
            id = 0,
            image = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRQfr1rXTMG-svUWWtNQvvas4TJ6LrEFTBP5dhwAl3hIJ9GCmM0SapCQ_pxR-bIUicblifb",
            title = "title 0",
            subtitle = "description 0",
            button = ListButton(
                title = "test"
            )
        ),
        ListElement(
            id = 1,
            image = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRQfr1rXTMG-svUWWtNQvvas4TJ6LrEFTBP5dhwAl3hIJ9GCmM0SapCQ_pxR-bIUicblifb",
            title = "title 1",
            subtitle = "description 1",
            button = ListButton(
                title = "test"
            )
        ),
        ListElement(
            id = 2,
            image = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRQfr1rXTMG-svUWWtNQvvas4TJ6LrEFTBP5dhwAl3hIJ9GCmM0SapCQ_pxR-bIUicblifb",
            title = "title",
            subtitle = "test",
            button = ListButton(
                title = "test"
            )
        ),
        ListElement(
            id = 3,
            image = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRQfr1rXTMG-svUWWtNQvvas4TJ6LrEFTBP5dhwAl3hIJ9GCmM0SapCQ_pxR-bIUicblifb",
            title = "title",
            subtitle = "test",
            button = ListButton(
                title = "test"
            )
        )
    )

    override suspend fun getList(): List<ListElement> = withContext(Dispatchers.IO) {
        return@withContext list
    }

    override suspend fun getElement(id: Long): ListElement {
        return list.find { it.id == id }!!
    }
}
