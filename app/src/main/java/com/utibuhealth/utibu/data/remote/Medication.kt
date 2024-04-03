package com.utibuhealth.utibu.data.remote

data class Medication(
    val medicationId: Int,
    val name: String,
    val description: String?,
    val stockLevel: Int
)

