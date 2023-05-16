package com.nyc.nycschools.data.dto

import com.nyc.nycschools.data.domain.ktx.extractSchoolSub
import com.nyc.nycschools.data.domain.models.School

/**
 * Created by peterx.theja on 2023-05-13.
 */
data class SchoolDto(
    val city: String,
    val zip: String,
    val school_name: String,
    val dbn: String,
    val requirement1_1: String
)

fun SchoolDto.toSchool(): School {
    val courseGrades = requirement1_1.extractSchoolSub()
    return School(
        city = city,
        schoolName = school_name,
        zip = zip,
        id = dbn,
        math = courseGrades.find { it.subject == "Math" }?.range ?:"",
        english = courseGrades.find { it.subject == "English" }?.range?:"",
        social = courseGrades.find { it.subject == "Social Studies" }?.range?:"",
        science = courseGrades.find { it.subject == "Science" }?.range?:""
    )
}