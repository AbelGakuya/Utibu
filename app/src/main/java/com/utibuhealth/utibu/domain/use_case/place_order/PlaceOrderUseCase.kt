package com.utibuhealth.utibu.domain.use_case.place_order

import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.Order
import com.utibuhealth.utibu.domain.repository.UtibuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlaceOrderUseCase @Inject constructor(
    private val repository: UtibuRepository
) {
    operator fun invoke(orderData: Order): Flow<Resource<Order>> = flow {
        try {
            // Emit loading state if needed
            emit(Resource.Loading)

            // Call the repository to place the order
            val response = repository.placeOrder(orderData)

            // Check if response data is not null before emitting success
            val resource = if (response.data != null) {
                Resource.Success(response.data!!)
            } else {
                Resource.Error("Order data is null")
            }

            // Emit the appropriate resource
            emit(resource)
        } catch (e: IOException) {
            // Handle IO exceptions
            emit(Resource.Error("Network error: ${e.message}"))
        } catch (e: HttpException) {
            // Handle HTTP exceptions
            emit(Resource.Error("HTTP error: ${e.message}"))
        }
    }
}