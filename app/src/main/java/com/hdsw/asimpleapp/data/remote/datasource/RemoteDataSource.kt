package com.hdsw.asimpleapp.data.remote.datasource

import com.hdsw.asimpleapp.data.model.Cat

interface RemoteDataSource {
    suspend fun getCats(): List<Cat>

    suspend fun getCatById(catId: String): Cat
}