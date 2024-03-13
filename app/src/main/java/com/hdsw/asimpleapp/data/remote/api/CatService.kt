package com.hdsw.asimpleapp.data.remote.api

import com.hdsw.asimpleapp.data.model.Cat
import retrofit2.http.GET
import retrofit2.http.Path

interface CatService {
    @GET("cats")
    suspend fun getCats(): List<Cat>
    @GET("/{catId}")
    suspend fun getCatById(@Path("catId") catId : String): Cat
}
