package com.hdsw.asimpleapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hdsw.asimpleapp.data.local.Constants
import com.hdsw.asimpleapp.data.local.datasource.LocalDataSource
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.remote.datasource.RemoteDataSource
import com.hdsw.asimpleapp.data.repository.impl.CatRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CatRepositoryImplTest {

    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: CatRepositoryImpl

    @Before
    fun setup() {
        repository = CatRepositoryImpl(localDataSource, remoteDataSource)
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
        verify(localDataSource).addAll(cats)

        Assert.assertNotNull(result)

    }

}
