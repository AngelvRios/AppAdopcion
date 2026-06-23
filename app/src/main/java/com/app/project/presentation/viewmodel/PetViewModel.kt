package com.app.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.project.domain.model.Pet
import com.app.project.domain.usecase.GetPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase
) : ViewModel() {

    private val _petsState = MutableStateFlow<PetsState>(PetsState.Loading)
    val petsState: StateFlow<PetsState> = _petsState

    init {
        loadPets()
    }

    private fun loadPets() {
        viewModelScope.launch {
            getPetsUseCase()
                .catch { e ->
                    _petsState.value = PetsState.Error(e.message ?: "Unknown error")
                }
                .collect { pets ->
                    _petsState.value = PetsState.Success(pets)
                }
        }
    }
}

sealed class PetsState {
    object Loading : PetsState()
    data class Success(val pets: List<Pet>) : PetsState()
    data class Error(val message: String) : PetsState()
}
