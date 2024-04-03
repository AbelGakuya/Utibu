package com.utibuhealth.utibu.presentation.registration

import com.utibuhealth.utibu.data.remote.User

sealed class RegisterViewState {
    object Loading : RegisterViewState()
    data class Success(val user: User) : RegisterViewState()
    data class Error(val message: String) : RegisterViewState()
}
