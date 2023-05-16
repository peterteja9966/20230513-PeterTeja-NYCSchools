package com.nyc.nycschools.presentation.schooldetails.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyc.nycschools.data.domain.models.School
import com.nyc.nycschools.presentation.schooldetails.states.SchoolDetailsState
import com.nyc.nycschools.presentation.schools.views.loadingIndicator
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
            SchoolDetail(school = school)
        }
        if (state.isLoading) {
            loadingIndicator()
        } else if (state.error.isNotBlank()) {
            errorMessage(state = state)
        }
    }
}

@Composable
fun errorMessage(state: SchoolDetailsState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun SchoolDetail(school: School) {
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
        if(school.english?.isNotEmpty() == true){
            Text(
                text = "English: "+school.english,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }

        if(school.math?.isNotEmpty() == true){
            Text(
                text = "Math: "+school.math,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }

        if(school.science?.isNotEmpty() == true){
            Text(
                text = "Science :"+school.science,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }

        if(school.social?.isNotEmpty() == true){
            Text(
                text = "Social: "+school.social,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }

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
