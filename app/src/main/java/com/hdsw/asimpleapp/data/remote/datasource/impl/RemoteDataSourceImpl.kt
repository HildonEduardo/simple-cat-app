package com.hdsw.asimpleapp.data.remote.datasource.impl

import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.remote.api.CatService
import com.hdsw.asimpleapp.data.remote.datasource.RemoteDataSource

/**
 * Created by hildon.lima<hildon.eduardo@gmail.com> on 13/03/24.
 */
class RemoteDataSourceImpl(val catApi: CatService): RemoteDataSource {

    override suspend fun getCats(): List<Cat> {
        return catApi.getCats()
    }

    override suspend fun getCatById(catId : String): Cat {
        return catApi.getCatById(catId)
    }

}