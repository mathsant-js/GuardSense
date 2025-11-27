package com.example.guardsense.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SensorRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun updateKeypadCode(newCode: String): Result<Unit> {
        return try {
            firestore.collection("Sensors").document("testsensors")
                .update("keyPadCode", newCode)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}