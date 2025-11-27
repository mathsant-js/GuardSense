package com.example.guardsense.data.repository

import com.example.guardsense.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserRepository(
    private val auth: FirebaseAuth = Firebase.auth,
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    suspend fun registerWithEmail(
        name: String,
        email: String,
        cpf: String,
        address: String,
        telephone: String,
        birthDate: String,
        password: String
    ): Result<User> {
        return try {
            val birthDate = dateFormat.parse(birthDate)
                ?: return Result.failure(Exception("Data de nascimento inválida"))

            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user!!

            firebaseUser.sendEmailVerification().await()
            val user = User(
                id = firebaseUser.uid,
                name = name,
                email = email,
                cpf = cpf,
                address = address,
                telephone = telephone,
                birthDate = birthDate,
                provider = "email"
            )

            firestore.collection("Users").document(firebaseUser.uid).set(user).await()
            auth.signOut()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginWithEmail(email: String, password: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user!!

            if (!user.isEmailVerified) {
                auth.signOut()
                return Result.failure(Exception("E-mail não verificado."))
            }

            Result.success(user.uid)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun saveOrUpdateUserFromFirebase(userProvider: FirebaseUser, provider: String): Result<User> {
        return try {
            val userDocRef = firestore.collection("Users").document(userProvider.uid)
            val document = userDocRef.get().await()
            val user: User

            if (document.exists()) {
                val existingUser = document.toObject(User::class.java)!!
                val updatedUser = existingUser.copy(
                    name = userProvider.displayName ?: existingUser.name,
                    email = userProvider.email ?: existingUser.email
                )
                if (updatedUser != existingUser) {
                    userDocRef.set(updatedUser).await()
                }
                user = updatedUser
            } else {
                user = User(
                    id = userProvider.uid,
                    name = userProvider.displayName ?: "",
                    email = userProvider.email ?: "",
                    cpf = "",
                    address = "",
                    telephone = "",
                    birthDate = Date(),
                    provider = provider
                )
                userDocRef.set(user).await()
            }
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getMicrosoftProviderBuilder(): OAuthProvider.Builder {
        return OAuthProvider.newBuilder("microsoft.com")
    }

}