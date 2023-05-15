package com.nyc.nycschools.presentation.nav

/**
 * Created by peterx.theja on 2023-05-13.
 */
sealed class Screen(val route: String) {
    object SchoolListScreen : Screen("school_list_screen")
    object SchoolDetailScreen : Screen("details/{schoolId}")
    companion object {
        private const val SchoolIdPlaceholder = "{schoolId}"

        fun schoolDetailsRoute(schoolId: String): String {
            return SchoolDetailScreen.route.replace(SchoolIdPlaceholder, schoolId)
        }
    }
}