package com.example.a207944_izzi_izwan_lab02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// UI-facing model used by the screens. Mirrors the entity but lives in the ViewModel layer.
data class MaterialPost(
    val id: Int = 0,
    val title: String,
    val courseCode: String,
    val materialType: String
)

class AppViewModel(private val repository: MaterialRepository) : ViewModel() {

    // Map each entity emitted by Room into a UI MaterialPost so screens stay decoupled from the DB layer.
    val posts: StateFlow<List<MaterialPost>> = repository.allPosts
        .map { entityList ->
            entityList.map { entity ->
                MaterialPost(
                    id = entity.id,
                    title = entity.title,
                    courseCode = entity.courseCode,
                    materialType = entity.materialType
                )
            }
        }
        // stateIn turns the cold Flow into a hot StateFlow tied to the ViewModel's lifecycle.
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addPost(post: MaterialPost) {
        // viewModelScope launches a coroutine that is automatically cancelled when the ViewModel is cleared.
        viewModelScope.launch {
            repository.insertPost(
                MaterialPostEntity(
                    title = post.title,
                    courseCode = post.courseCode,
                    materialType = post.materialType
                )
            )
        }
    }

    fun deletePost(post: MaterialPost) {
        viewModelScope.launch {
            repository.deletePost(
                MaterialPostEntity(
                    id = post.id,
                    title = post.title,
                    courseCode = post.courseCode,
                    materialType = post.materialType
                )
            )
        }
    }

    fun clearPosts() {
        viewModelScope.launch {
            repository.deleteAllPosts()
        }
    }
}

// Factory is required because AppViewModel has a constructor parameter (the Repository) that the default ViewModelProvider can't supply.
class AppViewModelFactory(private val repository: MaterialRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
