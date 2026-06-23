package com.app.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.project.domain.model.User
import com.app.project.domain.usecase.LoginUseCase
import com.app.project.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val token = loginUseCase(email, password)
            if (token != null) {
                _authState.value = AuthState.Authenticated(token)
            } else {
                _authState.value = AuthState.Error("Login failed")
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val success = registerUseCase(user)
            if (success) {
                _authState.value = AuthState.Registered
            } else {
                _authState.value = AuthState.Error("Registration failed")
            }
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Authenticated(val token: String) : AuthState()
    object Registered : AuthState()
    data class Error(val message: String) : AuthState()
}
