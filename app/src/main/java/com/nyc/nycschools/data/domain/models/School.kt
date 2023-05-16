package com.nyc.nycschools.data.domain.models

/**
 * Created by peterx.theja on 2023-05-13.
 */
data class School(
    val city: String,
    val zip: String,
    val schoolName: String,
    val id: String,
    val math: String?,
    val english: String?,
    val social: String?,
    val science: String?
)
