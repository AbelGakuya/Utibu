package com.utibuhealth.utibu.domain.use_case.register_user

import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.User
import com.utibuhealth.utibu.domain.repository.UtibuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: UtibuRepository
) {
    operator fun invoke(user: User): Flow<Resource<User>> = flow {
        try {
            // Emit loading state if needed
            emit(Resource.Loading)

            // Call the repository to register the user
            val response = repository.registerUser(user)

            // Check if response data is not null before emitting success
            val resource = if (response.data != null) {
                Resource.Success(response.data!!)
            } else {
                Resource.Error("User data is null")
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