package com.example.a207944_izzi_izwan_lab02


// Data models

data class UserData(
    val name: String = "",
    val matric: String = "",
    val fakulti: String = ""
)

data class MaterialPost(
    val title: String,
    val courseCode: String,
    val posterName: String,
    val posterMatric: String,
    val posterFakulti: String,
    val category: String = "Notes",         // e.g. "Notes", "Past Year", "Lab Report"
    val categoryShort: String = "Note",     // e.g. "Note", "PY", "Lab"
    val xpValue: Int = 80                   // XP awarded for this post
)
