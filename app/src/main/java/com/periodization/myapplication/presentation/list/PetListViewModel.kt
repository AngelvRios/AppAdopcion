package com.periodization.myapplication.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.periodization.myapplication.domain.usecase.GetPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetListViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PetListState())
    val state: StateFlow<PetListState> = _state.asStateFlow()

    init {
        getPets()
    }

    fun getPets() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            getPetsUseCase().onSuccess { pets ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    pets = pets
                )
            }.onFailure { exception ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: "An unknown error occurred"
                )
            }
        }
    }
}