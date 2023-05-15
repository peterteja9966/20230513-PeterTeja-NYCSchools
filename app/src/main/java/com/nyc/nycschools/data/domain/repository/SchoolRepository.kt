package com.nyc.nycschools.data.domain.repository

import com.nyc.nycschools.data.dto.SchoolDto
import com.nyc.nycschools.data.dto.Schools

/**
 * Created by peterx.theja on 2023-05-13.
 */
interface SchoolRepository {
    suspend fun getSchools(): List<SchoolDto>
    suspend fun getSchoolById(id: String): SchoolDto
}