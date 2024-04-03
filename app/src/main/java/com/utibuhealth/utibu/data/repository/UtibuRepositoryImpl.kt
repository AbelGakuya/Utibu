package com.utibuhealth.utibu.data.repository

import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.Order
import com.utibuhealth.utibu.data.remote.User
import com.utibuhealth.utibu.data.remote.UtibuApi
import com.utibuhealth.utibu.domain.repository.UtibuRepository
import javax.inject.Inject

class UtibuRepositoryImpl @Inject constructor(
    private val api: UtibuApi
): UtibuRepository {
    override suspend fun registerUser(userData: User): Resource<User> {
        return api.registerUser(userData)
    }

    override suspend fun placeOrder(orderData: Order): Resource<Order> {
        return api.placeOrder(orderData)
    }
}