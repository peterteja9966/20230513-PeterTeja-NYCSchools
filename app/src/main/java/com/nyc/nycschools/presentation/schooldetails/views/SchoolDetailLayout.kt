package com.nyc.nycschools.presentation.schooldetails.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyc.nycschools.data.domain.models.School
import com.nyc.nycschools.presentation.theme.NYCSchoolsTheme
import com.nyc.nycschools.presentation.viewmodel.SchoolViewModel

/**
 * Created by peterx.theja on 2023-05-13.
 */

@ExperimentalAnimationApi
@Composable
fun SchoolDetail(viewModel: SchoolViewModel = hiltViewModel(), id: String) {
    viewModel.getSchoolById(id = id)
    val state = viewModel.detailsState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.school?.let { school ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    textAlign = TextAlign.Center,
                    text = school.schoolName,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,
                    maxLines = 1
                )
                Text(
                    text = "ID: ${school.id}",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Text(
                    text = "City: ${school.city}",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Text(
                    text = "Zip: ${school.zip}",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }
        }
        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun SchoolDetail1(school: School) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = school.schoolName,
            style = MaterialTheme.typography.body2
        )
        Text(
            text = school.id,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = school.city,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = school.zip,
            style = MaterialTheme.typography.body1
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    NYCSchoolsTheme {
        val school = School(
            schoolName = "Test1",
            zip = "1234",
            id = "34567",
            city = "djkfhkjdshfj"
        )
        SchoolDetail1(school)
    }
}