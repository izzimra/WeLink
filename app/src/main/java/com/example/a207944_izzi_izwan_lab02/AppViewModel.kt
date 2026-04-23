package com.example.a207944_izzi_izwan_lab02

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {
    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData

    fun updateUser(name: String, matric: String, faculty: String) {
        _userData.value = UserData(name, matric, faculty)
    }
}