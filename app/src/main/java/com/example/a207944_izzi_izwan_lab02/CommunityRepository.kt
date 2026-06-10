package com.example.a207944_izzi_izwan_lab02

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

// Repository for the CLOUD data source. Same role as MaterialRepository,
// but talks to Firestore instead of Room. The ViewModel never sees Firestore directly.
class CommunityRepository {

    // getInstance() gives the app-wide Firestore client. No Context needed (unlike Room).
    private val db = FirebaseFirestore.getInstance()

    // A "collection" is like a table; documents inside it are like rows.
    private val collection = db.collection("community_materials")

    // Expose the live feed as a Flow. addSnapshotListener pushes a NEW list every
    // time the cloud data changes, so the UI updates in real time across all users.
    fun getCommunityMaterials(): Flow<List<CommunityMaterial>> = callbackFlow {
        val registration = collection
            .orderBy("timestamp", Query.Direction.DESCENDING) // newest first
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error) // stop the Flow if Firestore reports a problem
                    return@addSnapshotListener
                }
                // Convert raw documents into our Kotlin objects.
                val materials = snapshot?.toObjects(CommunityMaterial::class.java) ?: emptyList()
                trySend(materials) // emit the latest list to whoever is collecting
            }
        // awaitClose runs when the collector goes away; we detach the listener to avoid leaks.
        awaitClose { registration.remove() }
    }

    // Push a new material document to the cloud. add() generates a random document id.
    fun uploadMaterial(material: CommunityMaterial) {
        collection.add(material)
    }
}
