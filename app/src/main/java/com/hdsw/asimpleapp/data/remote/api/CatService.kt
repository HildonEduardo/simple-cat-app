package com.hdsw.asimpleapp.data.remote.api

import com.hdsw.asimpleapp.data.model.Cat
import retrofit2.http.GET

interface CatService {
    @GET("cats")
    suspend fun getCats(): List<Cat>
}
