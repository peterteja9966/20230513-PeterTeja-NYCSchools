package com.nyc.nycschools.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyc.nycschools.common.Resource
import com.nyc.nycschools.data.domain.use_cases.GetSchoolsUseCase
import com.nyc.nycschools.presentation.schooldetails.states.SchoolDetailsState
import com.nyc.nycschools.presentation.schools.SchoolListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by peterx.theja on 2023-05-13.
 */

@HiltViewModel
class SchoolViewModel
@Inject constructor(
    private val getSchoolsUseCase: GetSchoolsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SchoolListState())
    val state: State<SchoolListState>
        get() = _state

    private val _detailState = mutableStateOf(SchoolDetailsState())
    val detailsState: State<SchoolDetailsState>
        get() = _detailState

    init {
        loadSchools()
    }

    fun loadSchools() {
        getSchoolsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SchoolListState(schools = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SchoolListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SchoolListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSchoolById(id: String) {
        _detailState.value = _detailState.value.copy(isLoading = true)
        _detailState.value = _detailState.value.copy(
            school = state.value.schools.find { it.id == id }
        )
    }

}