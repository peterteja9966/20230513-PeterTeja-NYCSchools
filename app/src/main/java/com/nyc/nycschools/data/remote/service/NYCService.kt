package com.nyc.nycschools.data.remote.service

import com.nyc.nycschools.data.dto.SchoolDto
import retrofit2.http.GET

/**
 * Created by peterx.theja on 2023-05-13.
 */
interface NYCService {
    @GET("/resource/s3k6-pzi2.json")
    suspend fun getSchools(): List<SchoolDto>
}