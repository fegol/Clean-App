package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.ElementsDao
import com.example.data.db.entities.ElementEntity

@Database(entities = [ElementEntity::class], version = 1)
abstract class MyDB: RoomDatabase() {

    abstract fun getElementsDao(): ElementsDao
}