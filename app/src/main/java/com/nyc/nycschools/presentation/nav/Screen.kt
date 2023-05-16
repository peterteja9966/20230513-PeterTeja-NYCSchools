package com.nyc.nycschools.presentation.nav

/**
 * Created by peterx.theja on 2023-05-13.
 */
// classes to handle navigation with navcontroller in main presenter
sealed class Screen(val route: String) {
    object SchoolListScreen : Screen("school_list_screen")
    object SchoolDetailScreen : Screen("details/{schoolId}")
    companion object {
        private const val SchoolIdPlaceholder = "{schoolId}" //a schoolid used to pass it through schoolDetailsRoute before
        // navcontroller navigates to SchoolDetailScreen to hadnle it in backstack

        fun schoolDetailsRoute(schoolId: String): String {
            return SchoolDetailScreen.route.replace(SchoolIdPlaceholder, schoolId)
        }
    }
}