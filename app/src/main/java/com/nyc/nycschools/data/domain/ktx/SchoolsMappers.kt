package com.nyc.nycschools.data.domain.ktx

/**
 * Created by peterx.theja on 2023-05-14.
 */

fun String?.extractSchoolSub(): List<SchoolGrade> {
    if (this.isNullOrEmpty()) {
        return emptyList()
    }

    val regex = """(?<=: ).*""".toRegex()
    val extractedData = regex.find(this)?.value ?: ""

    return extractedData.split(", ").map { grade ->
        val subject = grade.substringBefore(" (").trim().takeIf { it.isNotEmpty() }
        val gradeRange = grade.substringAfter(" (").removeSuffix(")").trim().takeIf { it.isNotEmpty() }
        SchoolGrade(subject, gradeRange)
    }

}

data class SchoolGrade(val subject: String?, val range: String?)