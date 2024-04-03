package com.utibuhealth.utibu.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.User
import com.utibuhealth.utibu.domain.use_case.register_user.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    private val _registerState = MutableLiveData<RegisterViewState>()
    val registerState: LiveData<RegisterViewState> = _registerState

    fun registerUser(user: User) {
        viewModelScope.launch {
            registerUserUseCase(user)
                .collect { resource ->
                    _registerState.value = when (resource) {
                        is Resource.Loading -> {RegisterViewState.Loading}
                        is Resource.Success -> {RegisterViewState.Success(resource.data)}
                        is Resource.Error -> {RegisterViewState.Error(resource.message)}
                    }
                }
        }
    }
}