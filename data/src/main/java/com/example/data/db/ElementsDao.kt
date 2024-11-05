package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entities.ElementEntity

@Dao
interface ElementsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElement(element: ElementEntity)

    @Query("SELECT * FROM elements WHERE id=:id")
    suspend fun elementById(id: Long): ElementEntity?
}