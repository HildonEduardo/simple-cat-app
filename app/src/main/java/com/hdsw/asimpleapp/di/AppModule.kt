package com.hdsw.asimpleapp.di

import com.hdsw.asimpleapp.data.local.datasource.LocalDataSource
import com.hdsw.asimpleapp.data.remote.datasource.RemoteDataSource
import com.hdsw.asimpleapp.data.repository.impl.CatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): CatRepositoryImpl {
        return CatRepositoryImpl(localDataSource, remoteDataSource)
    }
}
