package com.nyc.nycschools.di

import com.nyc.nycschools.data.remote.service.NYCService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by peterx.theja on 2023-05-13.
 */
@Module
class TestSchoolModule {

    @Provides
    fun provideSchoolService(retrofit: Retrofit): NYCService =
        retrofit.create(NYCService::class.java)

}