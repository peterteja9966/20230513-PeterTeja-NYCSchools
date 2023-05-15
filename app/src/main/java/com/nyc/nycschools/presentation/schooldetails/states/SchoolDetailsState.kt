package com.nyc.nycschools.presentation.schooldetails.states

import com.nyc.nycschools.data.domain.models.School

/**
 * Created by peterx.theja on 2023-05-14.
 */
data class SchoolDetailsState(
    var school: School? = null,
    val error: String = "",
    var isLoading:Boolean = false
)