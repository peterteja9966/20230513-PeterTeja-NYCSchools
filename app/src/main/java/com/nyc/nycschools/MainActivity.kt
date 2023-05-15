package com.nyc.nycschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nyc.nycschools.presentation.nav.Screen
import com.nyc.nycschools.presentation.schooldetails.views.SchoolDetail
import com.nyc.nycschools.presentation.schools.views.SchoolHomeContent
import com.nyc.nycschools.presentation.theme.NYCSchoolsTheme
import com.nyc.nycschools.presentation.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SchoolListScreen.route
                    ) {
                        composable(
                            route = Screen.SchoolListScreen.route
                        ) {
                            SchoolHomeContent(navController)
                        }
                        composable(
                            route = Screen.SchoolDetailScreen.route,
                        ) { backStackEntry ->
                            val schoolId =
                                backStackEntry.arguments?.getString("schoolId").toString()
                            SchoolDetail(id = schoolId)
                        }
                    }
                }
            }
        }
    }
}