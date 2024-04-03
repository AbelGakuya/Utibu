package com.utibuhealth.utibu.data.remote

import com.utibuhealth.utibu.common.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UtibuApi {
    @POST("register")
    suspend fun registerUser(@Body userData: User): Resource<User>

    @POST("place-order")
    suspend fun placeOrder(@Body orderData: Order): Resource<Order>
}