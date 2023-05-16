package com.nyc.nycschools.data.domain.use_cases

import com.nyc.nycschools.common.Resource
import com.nyc.nycschools.data.domain.models.School
import com.nyc.nycschools.data.domain.repository.SchoolRepository
import com.nyc.nycschools.data.dto.toSchool
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by peterx.theja on 2023-05-13.
 */
//a domain layer to seggregate the positive and negative cases coming from repo
open class GetSchoolsUseCase @Inject constructor(
    private val repository: SchoolRepository
) {
    operator fun invoke(): Flow<Resource<List<School>>> = flow {
        try {
            emit(Resource.Loading())
            val schools = repository.getSchools().map { it.toSchool() }
            emit(Resource.Success(schools))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    } // the alternate could be a runcatching however, this impl could have a more control over exception handl
}