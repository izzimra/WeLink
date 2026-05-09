package com.example.a207944_izzi_izwan_lab02

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {
    private val _posts = MutableStateFlow<List<MaterialPost>>(emptyList())
    val posts: StateFlow<List<MaterialPost>> = _posts

    fun addPost(post: MaterialPost) {
        _posts.value = _posts.value + post
    }
}