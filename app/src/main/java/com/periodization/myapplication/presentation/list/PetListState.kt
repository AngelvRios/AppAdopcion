package com.periodization.myapplication.presentation.list

import com.periodization.myapplication.domain.model.Pet

data class PetListState(
    val isLoading: Boolean = false,
    val pets: List<Pet> = emptyList(),
    val error: String? = null
)