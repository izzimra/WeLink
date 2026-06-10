package com.example.a207944_izzi_izwan_lab02

// These classes mirror the JSON returned by https://openlibrary.org/search.json
// Field names MUST match the JSON keys so Gson can map them automatically.
// All fields are nullable/defaulted because any key may be missing in a response.

// The top-level object: { "docs": [ ... ] }
data class OpenLibraryResponse(
    val docs: List<BookDoc> = emptyList()
)

// One search result. "author_name" and "cover_i" use Open Library's exact key names.
data class BookDoc(
    val title: String? = null,
    val author_name: List<String>? = null,
    val first_publish_year: Int? = null,
    val cover_i: Int? = null
)
