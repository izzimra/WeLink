package com.example.a207944_izzi_izwan_lab02

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// @Database lists the entities and the schema version of the SQLite database.
@Database(entities = [MaterialPostEntity::class], version = 1, exportSchema = false)
abstract class WeLinkDatabase : RoomDatabase() {

    // Room generates this DAO implementation at build time.
    abstract fun materialPostDao(): MaterialPostDao

    companion object {
        // @Volatile makes the singleton visible to all threads immediately.
        @Volatile
        private var INSTANCE: WeLinkDatabase? = null

        fun getDatabase(context: Context): WeLinkDatabase {
            // Double-checked locking: build only once, share the same instance app-wide.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeLinkDatabase::class.java,
                    "welink_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
