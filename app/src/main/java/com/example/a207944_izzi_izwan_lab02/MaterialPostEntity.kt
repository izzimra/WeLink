package com.example.a207944_izzi_izwan_lab02

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity tells Room this class is a table; tableName names the SQLite table.
@Entity(tableName = "material_posts")
data class MaterialPostEntity(
    // @PrimaryKey with autoGenerate lets Room assign a unique id to every new row.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val courseCode: String,
    val materialType: String
)
