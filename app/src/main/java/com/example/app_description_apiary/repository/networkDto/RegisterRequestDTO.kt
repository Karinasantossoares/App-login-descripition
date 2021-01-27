package com.example.app_description_apiary.repository.networkDto

data class RegisterRequestDTO(
    val name: String,
    val lastName: String,
    val dateOfBirth: Long?
)