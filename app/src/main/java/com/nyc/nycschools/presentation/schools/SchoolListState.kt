package com.nyc.nycschools.presentation.schools

import com.nyc.nycschools.data.domain.models.School

/**
 * Created by peterx.theja on 2023-05-13.
 */
data class SchoolListState(
    val isLoading: Boolean = false,
    val schools: List<School> = emptyList(),
    val error: String = ""
)
