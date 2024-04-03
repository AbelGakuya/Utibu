package com.utibuhealth.utibu.data.remote

data class User(
    val userId: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val address: String?
)

