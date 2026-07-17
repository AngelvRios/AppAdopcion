@file:OptIn(ExperimentalMaterial3Api::class)
package com.app.project.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.project.domain.model.Pet
import com.app.project.presentation.viewmodel.PetViewModel

@Composable
fun AddEditPetScreen(
    viewModel: PetViewModel,
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit
) {
    val currentPet by viewModel.currentPet.collectAsState()
    
    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    LaunchedEffect(currentPet) {
        currentPet?.let {
            nombre = it.nombre
            especie = it.especie ?: ""
            edad = it.edad ?: ""
            descripcion = it.descripcion ?: ""
            imagenUrl = it.imagenUrl ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (currentPet == null) "Agregar Mascota" else "Editar Mascota", fontWeight = FontWeight.Bold) },
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = especie, onValueChange = { especie = it }, label = { Text("Especie") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = imagenUrl, onValueChange = { imagenUrl = it }, label = { Text("URL de Imagen") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = {
                    val pet = Pet(
                        id = currentPet?.id ?: 0,
                        nombre = nombre,
                        especie = especie,
                        raza = null,
                        edad = edad,
                        descripcion = descripcion,
                        imagenUrl = imagenUrl,
                        estado = "Disponible"
                    )
                    viewModel.savePet(pet, onSuccess = onSaveSuccess)
                },
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Guardar Mascota")
            }
        }
    }
}
