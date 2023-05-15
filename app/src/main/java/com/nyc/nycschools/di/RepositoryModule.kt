package com.nyc.nycschools.di

import com.nyc.nycschools.data.domain.repository.SchoolRepository
import com.nyc.nycschools.data.impl.SchoolListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

/**
 * Created by peterx.theja on 2023-05-13.
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSchoolRepository(schoolListRepoImpl: SchoolListRepoImpl): SchoolRepository
}