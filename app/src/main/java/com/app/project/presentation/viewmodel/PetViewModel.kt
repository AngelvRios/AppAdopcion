package com.app.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.project.domain.model.Pet
import com.app.project.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val createPetUseCase: CreatePetUseCase,
    private val updatePetUseCase: UpdatePetUseCase,
    private val deletePetUseCase: DeletePetUseCase
) : ViewModel() {

    private val _petsState = MutableStateFlow<PetsState>(PetsState.Loading)
    val petsState: StateFlow<PetsState> = _petsState

    private val _currentPet = MutableStateFlow<Pet?>(null)
    val currentPet = _currentPet.asStateFlow()

    init {
        loadPets()
    }

    fun loadPets() {
        viewModelScope.launch {
            _petsState.value = PetsState.Loading
            getPetsUseCase()
                .catch { e ->
                    _petsState.value = PetsState.Error(e.message ?: "Unknown error")
                }
                .collect { pets ->
                    _petsState.value = PetsState.Success(pets)
                }
        }
    }

    fun getPetById(id: Int) {
        viewModelScope.launch {
            val pet = getPetByIdUseCase(id)
            _currentPet.value = pet
        }
    }

    fun savePet(pet: Pet, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val success = if (pet.id == 0 || pet.id == -1) {
                createPetUseCase(pet)
            } else {
                updatePetUseCase(pet.id, pet)
            }
            if (success) {
                loadPets()
                onSuccess()
            }
        }
    }

    fun deletePet(id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val success = deletePetUseCase(id)
            if (success) {
                loadPets()
                onSuccess()
            }
        }
    }
}

sealed class PetsState {
    object Loading : PetsState()
    data class Success(val pets: List<Pet>) : PetsState()
    data class Error(val message: String) : PetsState()
}
