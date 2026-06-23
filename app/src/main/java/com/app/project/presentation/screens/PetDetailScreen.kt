package com.app.project.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.project.domain.model.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    pet: Pet,
    onBack: () -> Unit,
    onAdopt: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pet.nombre) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = pet.imagenUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = pet.nombre, style = MaterialTheme.typography.headlineMedium)
            Text(text = "${pet.especie} - ${pet.raza}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Edad: ${pet.edad} años", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pet.descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onAdopt,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Solicitar Adopción")
            }
        }
    }
}
