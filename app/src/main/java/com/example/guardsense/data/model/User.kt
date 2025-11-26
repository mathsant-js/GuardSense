package com.example.guardsense.data.model

import java.util.Date

data class User(
    val id: String,
    val name: String,
    val email: String,
    val cpf: String,
    val address: String,
    val telephone: String,
    val birthDate: Date,
    val provider: String,
)
