package com.hdsw.asimpleapp.di

import android.content.Context
import androidx.room.Room
import com.hdsw.asimpleapp.data.local.dao.CatDao
import com.hdsw.asimpleapp.data.local.database.CatDatabase
import com.hdsw.asimpleapp.data.local.datasource.LocalDataSource
import com.hdsw.asimpleapp.data.local.datasource.impl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideCatDao(appDatabase: CatDatabase): CatDao {
        return appDatabase.catDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(context, CatDatabase::class.java, "cat-database").build()
    }

    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideLocalDataSource(catDao: CatDao): LocalDataSource {
        return LocalDataSourceImpl(catDao)
    }
}