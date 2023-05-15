package com.nyc.nycschools.presentation

import com.nyc.nycschools.common.Resource
import com.nyc.nycschools.data.domain.models.School
import com.nyc.nycschools.data.domain.repository.SchoolRepository
import com.nyc.nycschools.data.domain.use_cases.GetSchoolsUseCase
import com.nyc.nycschools.presentation.viewmodel.SchoolViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

/**
 * Created by peterx.theja on 2023-05-13.
 */
@RunWith(JUnit4::class)
class SchoolListViewModelTest @Inject constructor(private val repository: SchoolRepository, private val useCase: GetSchoolsUseCase) {

    private lateinit var viewModel: SchoolViewModel

    @Before
    fun setup() {
        viewModel = SchoolViewModel(useCase)
    }

    @Test
    fun `loadSchools should update state with schools on success`() {
        val schools = listOf(
            School("1", "School 1","ee",""),
            School("2", "School 2","ww","")
        )
        val successResult = Resource.Success(schools)

        // Act
        viewModel.loadSchools()
        // Assert
        val state = viewModel.state.value
        assertEquals(state.schools, schools)
        assertEquals(state.isLoading, false)
        assertEquals(state.error, null)
    }
}
