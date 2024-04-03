package com.utibuhealth.utibu.data.remote

import java.util.Date

data class Payment(
    val paymentId: Int,
    val invoiceId: Int,
    val amount: Double,
    val paymentDate: Date
)

