package com.hdsw.asimpleapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hdsw.asimpleapp.data.model.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getAll(): Flow<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cats: List<Cat>)

    @Query("SELECT * FROM cats WHERE id = :catId")
    fun getCatById(catId: String): Flow<Cat>
}
