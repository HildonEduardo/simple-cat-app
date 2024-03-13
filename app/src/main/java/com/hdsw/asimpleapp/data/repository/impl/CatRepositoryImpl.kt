package com.hdsw.asimpleapp.data.repository.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hdsw.asimpleapp.data.local.Constants
import com.hdsw.asimpleapp.data.local.datasource.LocalDataSource
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.remote.datasource.RemoteDataSource
import com.hdsw.asimpleapp.data.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource //: remote - CatService: not used, mockup response
) : CatRepository {
    private val gson = Gson()
    override fun getCats(): Flow<List<Cat>> = flow {
        val itemType = object : TypeToken<List<Cat>>() {}.type
        val cats = gson.fromJson<List<Cat>>(Constants.JSON_DATA, itemType)
        localDataSource.addAll(cats)
        emit(cats)
    }

    override fun getCat(catId: String): Flow<Cat> {
        return localDataSource.getCatById(catId)
    }
}
