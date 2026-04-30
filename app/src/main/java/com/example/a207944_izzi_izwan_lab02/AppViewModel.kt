package com.example.a207944_izzi_izwan_lab02
// ── ViewModel ────────────────────────────────────────────────────────────────

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {

    // User profile state
    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData

    fun updateUser(name: String, matric: String, faculty: String) {
        _userData.value = UserData(name, matric, faculty)
    }

    // Shared posts list state
    private val _posts = MutableStateFlow<List<MaterialPost>>(emptyList())
    val posts: StateFlow<List<MaterialPost>> = _posts

    fun addPost(post: MaterialPost) {
        _posts.value = _posts.value + post
    }
}