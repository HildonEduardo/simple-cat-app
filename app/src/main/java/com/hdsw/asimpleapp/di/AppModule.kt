package com.hdsw.asimpleapp.di

import android.content.Context
import androidx.room.Room
import com.hdsw.asimpleapp.data.local.dao.CatDao
import com.hdsw.asimpleapp.data.local.database.CatDatabase
import com.hdsw.asimpleapp.data.remote.api.CatService
import com.hdsw.asimpleapp.data.remote.network.NetworkClient
import com.hdsw.asimpleapp.data.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatService(retrofit: Retrofit): CatService {
        return retrofit.create(CatService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return NetworkClient.create(context)
    }

    @Provides
    @Singleton
    fun provideCatDao(appDatabase: CatDatabase): CatDao {
        return appDatabase.catDao()
    }

    @Provides
    @Singleton
    fun provideCatRepository(catService: CatService, catDao: CatDao): CatRepository {
        return CatRepository(catService, catDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(context, CatDatabase::class.java, "cat-database").build()
    }

    @Provides
    fun provideDispatcherIO() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}
