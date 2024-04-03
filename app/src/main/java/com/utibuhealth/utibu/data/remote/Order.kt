package com.utibuhealth.utibu.data.remote

import java.util.Date

data class Order(
    val orderId: Int,
    val userId: Int,
    val medicationId: Int,
    val quantity: Int,
    val orderDate: Date,
    val status: String
)

