package com.hdsw.asimpleapp.data.repository

import com.hdsw.asimpleapp.data.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCats(): Flow<List<Cat>>

    fun getCat(catId: String): Flow<Cat>
}