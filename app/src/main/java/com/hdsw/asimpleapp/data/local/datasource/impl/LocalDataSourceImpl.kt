package com.hdsw.asimpleapp.data.local.datasource.impl

import com.hdsw.asimpleapp.data.local.dao.CatDao
import com.hdsw.asimpleapp.data.local.datasource.LocalDataSource
import com.hdsw.asimpleapp.data.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: CatDao) : LocalDataSource {
    override fun getAllCats(): Flow<List<Cat>> {
        return dao.getAll()
    }

    override fun getCatById(catId: String): Flow<Cat> {
        return dao.getCatById(catId)
    }

    override suspend fun addAll(cats: List<Cat>) {
        dao.insertAll(cats)
    }

}