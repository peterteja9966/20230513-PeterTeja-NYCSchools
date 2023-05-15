package com.nyc.nycschools.data.impl

import com.nyc.nycschools.data.domain.repository.SchoolRepository
import com.nyc.nycschools.data.dto.SchoolDto
import com.nyc.nycschools.data.remote.service.NYCService
import javax.inject.Inject

/**
 * Created by peterx.theja on 2023-05-13.
 */
class SchoolListRepoImpl @Inject constructor(private val service: NYCService) : SchoolRepository {

    override suspend fun getSchools(): List<SchoolDto> {
        return service.getSchools()
    }

    override suspend fun getSchoolById(id: String): SchoolDto {
        return SchoolDto(school_name = "", zip = "", city = "", dbn = id)
    }
}