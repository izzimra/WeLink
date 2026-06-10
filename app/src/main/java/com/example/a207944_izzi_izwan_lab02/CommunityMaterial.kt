package com.example.a207944_izzi_izwan_lab02

// Plain data class for a material shared to the public cloud feed.
// NOTE: every field needs a default value. Firestore needs an empty (no-arg)
// constructor to rebuild objects when reading documents back from the cloud.
data class CommunityMaterial(
    val title: String = "",
    val courseCode: String = "",
    val materialType: String = "",
    val uploader: String = "",
    val timestamp: Long = 0L
)
