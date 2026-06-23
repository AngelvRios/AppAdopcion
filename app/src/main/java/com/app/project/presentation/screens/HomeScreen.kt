package com.app.project.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.project.domain.model.Pet
import com.app.project.presentation.viewmodel.PetViewModel
import com.app.project.presentation.viewmodel.PetsState

@Composable
fun HomeScreen(
    viewModel: PetViewModel,
    onPetClick: (Pet) -> Unit
) {
    val petsState by viewModel.petsState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Adopción de Mascotas") })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val state = petsState) {
                is PetsState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize())
                }
                is PetsState.Success -> {
                    LazyColumn {
                        items(state.pets) { pet ->
                            PetItem(pet = pet, onClick = { onPetClick(pet) })
                        }
                    }
                }
                is PetsState.Error -> {
                    Text(text = state.message, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = pet.imagenUrl,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = pet.nombre, style = MaterialTheme.typography.titleLarge)
                Text(text = "${pet.especie} - ${pet.raza}")
                Text(text = "Edad: ${pet.edad} años")
            }
        }
    }
}
