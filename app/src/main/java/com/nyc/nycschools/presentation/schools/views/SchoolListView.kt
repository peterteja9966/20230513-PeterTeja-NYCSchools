package com.nyc.nycschools.presentation.schools.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nyc.nycschools.data.domain.models.School
import com.nyc.nycschools.presentation.nav.Screen
import com.nyc.nycschools.presentation.schools.SchoolListState
import com.nyc.nycschools.presentation.theme.NYCSchoolsTheme
import com.nyc.nycschools.presentation.theme.Typography
import com.nyc.nycschools.presentation.viewmodel.SchoolViewModel

/**
 * Created by peterx.theja on 2023-05-13.
 */

@Composable
fun SchoolHomeContent(
    navController: NavController,
    viewModel: SchoolViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val isInitialLoad = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isInitialLoad.value = true
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (isInitialLoad.value && state.isLoading) {
            loadingIndicator()
        } else if (state.schools.isEmpty()) {
            emptyListMessage()
        } else if (state.error.isNotBlank()) {
            errorMessage(state = state)
        } else {
            lazyList(state = state, navController = navController)
        }
    }
}

@Composable
fun SchoolListItem(
    school: School,
    onItemClick: (School) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(school) },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {

        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = school.schoolName,
                    style = Typography.body2,
                    color = Color.Black,
                    maxLines = 1
                )
                Text(text = school.city, style = Typography.body1, color = Color.Black)
                Text(text = school.zip, style = Typography.body1, color = Color.Black)
            }
        }
    }
}

@Composable
fun lazyList(state: SchoolListState, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding =
        PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(state.schools) { school ->
            SchoolListItem(
                school = school,
                onItemClick = {
                    val route = Screen.schoolDetailsRoute(school.id)
                    navController.navigate(
                        route = route
                    )
                }
            )
        }
    }
}

@Composable
fun emptyListMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Schools here, try again",
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun errorMessage(state: SchoolListState) {
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
fun loadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    NYCSchoolsTheme {
        val school =
            School(
                schoolName = "NYC School",
                city = "This is to test  school metadata",
                zip = "8247070294",
                id = "123",
                math = "123",
                social = "256",
                science = "67",
                english = "67"
            )

        SchoolListItem(school = school, onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NYCSchoolsTheme {

    }
}