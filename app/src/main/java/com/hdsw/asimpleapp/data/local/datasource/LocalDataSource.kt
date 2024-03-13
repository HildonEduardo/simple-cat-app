package com.hdsw.asimpleapp.data.local.datasource

import com.hdsw.asimpleapp.data.model.Cat
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllCats(): Flow<List<Cat>>
    fun getCatById(catId: String): Flow<Cat>

    suspend fun addAll(cats : List<Cat>)
}