package com.example.guardsense.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardsense.data.model.User
import com.example.guardsense.data.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Date

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {
    var uiState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    fun register(nome: String, email: String, cpf: String, address: String, telephone: String, birthDate: Date, password: String) {
        viewModelScope.launch {
            uiState = AuthState.Loading
            val res = repository.registerWithEmail(
                nome,
                email,
                cpf,
                address,
                telephone,
                birthDate,
                password
            )
            uiState = if (res.isSuccess) AuthState.Success(res.getOrNull()!!) else AuthState.Error(
                res.exceptionOrNull()?.message ?: "Erro"
            )
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            uiState = AuthState.Loading
            val res = repository.loginWithEmail(email, password)
            uiState = if (res.isSuccess) {
                val uid = res.getOrNull()!!
                val doc = repository.firestore.collection("Users").document(uid).get().await()
                val user = doc.toObject(User::class.java)
                if (user != null) {
                    AuthState.Success(user)
                } else {
                    val newUser = User(
                        id = uid,
                        name = Firebase.auth.currentUser?.displayName ?: "",
                        email = email,
                        cpf = "",
                        address = "",
                        telephone = "",
                        birthDate = Date(),
                        provider = "email"
                    )
                    repository.firestore.collection("Users").document(uid).set(newUser).await()
                    AuthState.Success(newUser)
                }
            } else {
                AuthState.Error(res.exceptionOrNull()?.message ?: "Erro")
            }
        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        viewModelScope.launch {
            uiState = AuthState.Loading
            try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                val firebaseUser = authResult.user!!

                if (!firebaseUser.isEmailVerified) firebaseUser.sendEmailVerification()

                val res = repository.saveOrUpdateUserFromFirebase(firebaseUser, "google")
                uiState = if (res.isSuccess) AuthState.Success(res.getOrNull()!!) else AuthState.Error(res.exceptionOrNull()?.message ?: "Erro")
            } catch (e: Exception) {
                uiState = AuthState.Error(e.message ?: "Erro")
            }
        }
    }

    fun startMicrosoftSignIn(activity: ComponentActivity) {
        val provider = repository.getMicrosoftProviderBuilder().build()

        uiState = AuthState.Loading
        Firebase.auth.startActivityForSignInWithProvider(activity, provider)
            .addOnSuccessListener { result ->
                viewModelScope.launch {
                    val firebaseUser = result.user!!
                    val res = repository.saveOrUpdateUserFromFirebase(firebaseUser, "microsoft.com")
                    uiState = if (res.isSuccess) {
                        AuthState.Success(res.getOrNull()!!)
                    } else {
                        AuthState.Error(res.exceptionOrNull()?.message ?: "Erro")
                    }
                }
            }
            .addOnFailureListener { e ->
                uiState = AuthState.Error(e.message ?: "Erro")
            }
    }
}