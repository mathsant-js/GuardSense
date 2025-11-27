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
    object NeedsEmailVerification : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    object Success : ProfileState()
    data class Error(val message: String) : ProfileState()
}

class AuthViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {
    var uiState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    var profileUiState by mutableStateOf<ProfileState>(ProfileState.Idle)
        private set

    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var cpf by mutableStateOf("")
        private set

    var address by mutableStateOf("")
        private set

    var telephone by mutableStateOf("")
        private set

    var birthDate by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateName(v: String) { name = v}
    fun updateEmail(v: String) { email = v}
    fun updateCPF(v: String) { cpf = v}
    fun updateAddress(v: String) { address = v}
    fun updateTelephone(v: String) { telephone = v}
    fun updateBirthDate(v: String) { birthDate = v}
    fun updatePassword(v: String) { password = v}

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            val firebaseUser = Firebase.auth.currentUser
            if (firebaseUser != null) {
                uiState = AuthState.Loading
                try {
                    val userDoc = repository.firestore.collection("Users").document(firebaseUser.uid).get().await()
                    if (userDoc.exists()) {
                        val user = userDoc.toObject(User::class.java)!!
                        uiState = AuthState.Success(user)
                    } else {
                        // This may happen if the user is authenticated but their data was deleted or not created.
                        // We sign them out to force a clean login.
                        uiState = AuthState.Error("User data is missing. Please log in again.")
                        Firebase.auth.signOut()
                    }
                } catch (e: Exception) {
                    uiState = AuthState.Error("Failed to load user data. ${e.message}")
                    Firebase.auth.signOut()
                }
            } else {
                uiState = AuthState.Idle
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            uiState = AuthState.Loading
            val res = repository.registerWithEmail(
                name,
                email,
                cpf,
                address,
                telephone,
                birthDate,
                password
            )
            uiState = if (res.isSuccess) {
                AuthState.NeedsEmailVerification
            } else {
                AuthState.Error(res.exceptionOrNull()?.message ?: "Erro ao registrar.")
            }
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

    fun updatePasswordAccount(newPassword: String) {
        viewModelScope.launch {
            profileUiState = ProfileState.Loading
            val result = repository.updatePassword(newPassword)
            profileUiState = if (result.isSuccess) {
                ProfileState.Success
            }
            else {
                ProfileState.Error(result.exceptionOrNull()?.message ?: "Erro ao atualizar a senha.")
            }
        }
    }

    fun resetProfileState() {
        profileUiState = ProfileState.Idle
    }

    fun logout() {
        Firebase.auth.signOut()
        uiState = AuthState.Idle
    }
}