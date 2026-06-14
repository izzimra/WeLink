package com.example.a207944_izzi_izwan_lab02

import kotlinx.coroutines.flow.Flow

// Repository is the single source of truth between the ViewModel and the DAO (MVVM + Repository pattern).
class MaterialRepository(private val dao: MaterialPostDao) {

    // Expose the Flow from the DAO so observers get automatic updates on every DB change.
    val allPosts: Flow<List<MaterialPostEntity>> = dao.getAllPosts()

    suspend fun insertPost(post: MaterialPostEntity) {
        dao.insertPost(post)
    }

    suspend fun deletePost(post: MaterialPostEntity) {
        dao.deletePost(post)
    }

    suspend fun deleteAllPosts() {
        dao.deleteAllPosts()
    }
}
