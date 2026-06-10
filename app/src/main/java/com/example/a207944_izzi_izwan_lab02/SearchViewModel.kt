package com.example.a207944_izzi_izwan_lab02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel for the Open Library search screen. No factory needed:
// RetrofitClient is a global singleton, so there are no constructor arguments.
class SearchViewModel : ViewModel() {

    // The list of books returned by the API.
    private val _results = MutableStateFlow<List<BookDoc>>(emptyList())
    val results: StateFlow<List<BookDoc>> = _results

    // True while the network request is in flight (drives a loading spinner).
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Holds an error message when the call fails, null otherwise.
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun search(query: String) {
        if (query.isBlank()) return
        // Launch on viewModelScope so the suspend network call runs off the main thread.
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // The actual internet call. Suspends here until the server responds.
                val response = RetrofitClient.api.searchBooks(query)
                _results.value = response.docs
            } catch (e: Exception) {
                // Networks fail (no wifi, timeout, server down) — never let it crash the app.
                _error.value = "Couldn't fetch books. Check your connection."
            } finally {
                _isLoading.value = false // always stop the spinner, success or fail
            }
        }
    }
}
