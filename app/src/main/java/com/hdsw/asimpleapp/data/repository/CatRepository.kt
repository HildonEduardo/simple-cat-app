package com.hdsw.asimpleapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hdsw.asimpleapp.data.local.Constants
import com.hdsw.asimpleapp.data.local.dao.CatDao
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.remote.api.CatService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catService: CatService,//not used, mockup response
    private val catDao: CatDao
) {
    private val gson = Gson()
    fun getCats(): Flow<List<Cat>> = flow {
        val itemType = object : TypeToken<List<Cat>>() {}.type
        val cats = gson.fromJson<List<Cat>>(Constants.JSON_DATA, itemType)
        catDao.insertAll(cats)
        emit(cats)
    }

    fun getCat(catId: String): Flow<Cat> {
        return catDao.getCatById(catId)
    }
}
