package com.example.a207944_izzi_izwan_lab02

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// The API "contract". Like a Room DAO, but for the internet:
// we declare WHAT we want; Retrofit generates HOW to fetch it.
interface OpenLibraryApi {

    // GET https://openlibrary.org/search.json?q=<query>&limit=20
    // @Query adds "?q=..." to the URL. suspend = runs off the main thread via coroutines.
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20
    ): OpenLibraryResponse
}

// A single shared Retrofit client. 'object' makes this a lazy singleton.
object RetrofitClient {
    private const val BASE_URL = "https://openlibrary.org/"

    // Build Retrofit once: base URL + Gson converter to parse JSON into our models.
    val api: OpenLibraryApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryApi::class.java)
    }
}
