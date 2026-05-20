package com.example.a207944_izzi_izwan_lab02

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// @Dao marks this interface as a Data Access Object; Room generates the SQL implementation.
@Dao
interface MaterialPostDao {

    // @Insert auto-generates an INSERT statement for the entity.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: MaterialPostEntity)

    // @Query returns a Flow so the UI is notified automatically on every table change.
    @Query("SELECT * FROM material_posts ORDER BY id DESC")
    fun getAllPosts(): Flow<List<MaterialPostEntity>>

    // @Query for DELETE — wipes the entire table when called.
    @Query("DELETE FROM material_posts")
    suspend fun deleteAllPosts()
}
