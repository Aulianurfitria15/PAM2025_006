package com.example.tugasakhirpam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhirpam.data.model.User
import com.example.tugasakhirpam.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginResult = MutableStateFlow<User?>(null)
    val loginResult = _loginResult.asStateFlow()

    fun register(username: String, password: String, role: String) {
        viewModelScope.launch {
            val user = User(
                username = username,
                password = password,
                role = role
            )
            userRepository.insertUser(user)
            // ❗ TIDAK set loginResult
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = userRepository.login(username, password)
        }
    }

    fun logout() {
        _loginResult.value = null
    }

    fun clearLoginResult() {
        _loginResult.value = null
    }
}
