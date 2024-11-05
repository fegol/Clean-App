package com.example.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elements")
data class ElementEntity(
    @PrimaryKey
    val id: Long,
    val read: Boolean,
    val like: Boolean
)