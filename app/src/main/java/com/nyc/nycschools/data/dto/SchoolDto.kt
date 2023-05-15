package com.nyc.nycschools.data.dto

import com.nyc.nycschools.data.domain.models.School

/**
 * Created by peterx.theja on 2023-05-13.
 */
data class SchoolDto(
    val city: String,
    val zip: String,
    val school_name: String,
    val dbn: String
)

fun SchoolDto.toSchool(): School {
    return School(
        city = city,
        schoolName = school_name,
        zip = zip,
        id = dbn
    )
}