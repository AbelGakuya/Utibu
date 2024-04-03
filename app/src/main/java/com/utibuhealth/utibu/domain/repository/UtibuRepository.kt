package com.utibuhealth.utibu.domain.repository

import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.Order
import com.utibuhealth.utibu.data.remote.User
import retrofit2.http.Body

interface UtibuRepository {
    suspend fun registerUser(userData: User): Resource<User>

    suspend fun placeOrder(orderData: Order): Resource<Order>
}