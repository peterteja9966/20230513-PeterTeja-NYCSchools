package com.nyc.nycschools.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.State
import com.nyc.nycschools.common.Resource
import com.nyc.nycschools.data.domain.models.School
import com.nyc.nycschools.data.domain.repository.SchoolRepository
import com.nyc.nycschools.data.domain.use_cases.GetSchoolsUseCase
import com.nyc.nycschools.data.dto.SchoolDto
import com.nyc.nycschools.presentation.schooldetails.states.SchoolDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Created by peterx.theja on 2023-05-16.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SchoolViewModel

    lateinit var schoolUsecase: GetSchoolsUseCase

    @Mock
    var schoolRepo: SchoolRepository = mock(SchoolRepository::class.java)

    @Mock
    private lateinit var mockState: State<SchoolDetailsState>

    @Mock
    private val mockSchools: List<School> = listOf(
        School("School 1", "2323", "test", "34", "(83-100)", "(87-100)", "(90-100)", "(88-100)")
    )

    @get:Rule
    val int = InstantTaskExecutorRule()


    @Captor
    private lateinit var stateCaptor: ArgumentCaptor<SchoolDetailsState>


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        schoolUsecase = GetSchoolsUseCase(schoolRepo)
        viewModel = SchoolViewModel(schoolUsecase)
    }


    @Test
    fun test_GetSchools_Loading_State() = runTest {
        val mockedSchoolsDto = listOf(
            SchoolDto(
                "School 1",
                "2323",
                "test",
                "34",
                "Course Grades: English (87-100), Math (83-100), Social Studies (90-100), Science (88-100)"
            )
        )

        val mockedSchools = listOf(
            School("School 1", "2323", "test", "34", "(83-100)", "(87-100)", "(90-100)", "(88-100)")
        )

        `when`(schoolRepo.getSchools()).thenReturn(mockedSchoolsDto)

        val resultFlow = schoolUsecase.invoke()
        val states = mutableListOf<Resource<List<School>>>()

        // Collect emitted by the flow
        resultFlow.collect { state ->
            states.add(state)
        }


        val expectedLoadingState = Resource.Loading<List<School>>(null)
        val actualLoadingState = states.first()

        // Assert the intermediate states
        assertEquals(expectedLoadingState::class, actualLoadingState::class)
        assertEquals(expectedLoadingState.data, actualLoadingState.data)


        val expectedSuccessState = Resource.Success(mockedSchools)
        val actualSuccessState = states.last()

        assertEquals(expectedSuccessState::class, actualSuccessState::class)
    }


    @Test
    fun test_GetSchools_Data_State() = runTest {
        val mockedSchoolsDto = listOf(
            SchoolDto(
                "School 1",
                "2323",
                "test",
                "34",
                "Course Grades: English (87-100), Math (83-100), Social Studies (90-100), Science (88-100)"
            )
        )

        val mockedSchools = listOf(
            School("School 1", "2323", "test", "34", "(83-100)", "(87-100)", "(90-100)", "(88-100)")
        )

        `when`(schoolRepo.getSchools()).thenReturn(mockedSchoolsDto)

        val resultFlow = schoolUsecase.invoke()
        val states = mutableListOf<Resource<List<School>>>()

        // Collect states emitted by the flow
        resultFlow.collect { state ->
            states.add(state)
        }

        val expectedSuccessState = Resource.Success(mockedSchools)
        val actualSuccessState = states.last()

        assertEquals(expectedSuccessState::class, actualSuccessState::class)
    }


    @Test
    fun test_ErrorCases() = runTest {
        val mockedErrorMessage = "An error occurred"
        val exception = IOException(mockedErrorMessage)

        `when`(schoolRepo.getSchools()).thenAnswer { throw exception }

        val resultFlow = schoolUsecase.invoke()
        val states = mutableListOf<Resource<List<School>>>()

        // Collecting  states emitted by the flow
        resultFlow.collect { state ->
            states.add(state)
        }

        // Collecting  states emitted by the flow
        resultFlow.collect { state ->
            states.add(state)
        }

        val expectedErrorState = Resource.Error<List<School>>(mockedErrorMessage)
        val actualErrorState = states.last()

        // Assert the error state
        assertEquals(expectedErrorState::class, actualErrorState::class)
    }

    @Test
    fun `test getSchoolById with valid id`() {
        val validId = "123"
        val expectedSchool = School(
            id = validId,
            schoolName = "",
            english = "",
            science = "",
            social = "",
            math = "",
            city = "",
            zip = ""
        )

        // Mock the initial state
        `when`(mockState.value).thenReturn(SchoolDetailsState(school  = expectedSchool))

        // Mock the find method to return the expected school
        `when`(mockSchools.find { it.id == validId }).thenReturn(expectedSchool)

        // Call the function
        viewModel.getSchoolById(validId)

        // Verify that the state's value property was accessed
        verify(mockState).value
    }
        @After
        fun tearDown() {
            Dispatchers.resetMain()
        }
}