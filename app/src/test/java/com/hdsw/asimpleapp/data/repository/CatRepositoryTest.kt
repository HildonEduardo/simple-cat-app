package com.hdsw.asimpleapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hdsw.asimpleapp.data.local.Constants
import com.hdsw.asimpleapp.data.local.dao.CatDao
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.remote.api.CatService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CatRepositoryTest {

    @Mock
    private lateinit var catService: CatService

    @Mock
    private lateinit var catDao: CatDao

    private lateinit var repository: CatRepository

    @Before
    fun setup() {
        repository = CatRepository(catService, catDao)
    }

    @Test
    fun `test getCats success`() = runBlocking {
        // Mock the response from the service
        val gson = Gson()
        val itemType = object : TypeToken<List<Cat>>() {}.type
        val cats = gson.fromJson<List<Cat>>(Constants.JSON_DATA, itemType)

        // Call the repository method
        val result = repository.getCats().first()

        // Verify that the DAO method was called to insert the data
        verify(catDao).insertAll(cats)

        Assert.assertNotNull(result)

    }

}
