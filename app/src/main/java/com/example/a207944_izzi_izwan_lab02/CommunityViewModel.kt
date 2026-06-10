package com.example.a207944_izzi_izwan_lab02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

// ViewModel for the cloud feed. Notice there is NO factory here:
// CommunityRepository needs no constructor arguments (Firestore is a global singleton),
// so the default viewModel() can build this directly. Contrast with AppViewModel + Room.
class CommunityViewModel : ViewModel() {

    private val repository = CommunityRepository()

    // Turn the cloud Flow into a lifecycle-aware StateFlow the UI can collect.
    val materials: StateFlow<List<CommunityMaterial>> =
        repository.getCommunityMaterials()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // Called when the user shares a material to the community.
    fun share(material: CommunityMaterial) {
        repository.uploadMaterial(material)
    }
}
