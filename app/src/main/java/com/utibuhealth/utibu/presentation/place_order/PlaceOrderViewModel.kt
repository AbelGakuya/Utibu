package com.utibuhealth.utibu.presentation.place_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.Order
import com.utibuhealth.utibu.domain.use_case.place_order.PlaceOrderUseCase
import com.utibuhealth.utibu.presentation.registration.RegisterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceOrderViewModel @Inject constructor(
    private val placeOrderUseCase: PlaceOrderUseCase
) : ViewModel() {

    private val _placeOrderState = MutableLiveData<PlaceOrderViewState>()
    val placeOrderState: LiveData<PlaceOrderViewState> = _placeOrderState

    fun placeOrder(orderData: Order) {
        viewModelScope.launch {
            placeOrderUseCase(orderData).collect { resource ->
                _placeOrderState.value = when (resource) {
                    is Resource.Loading -> {PlaceOrderViewState.Loading}
                    is Resource.Success -> {PlaceOrderViewState.Success(resource.data)}
                    is Resource.Error -> {PlaceOrderViewState.Error(resource.message)}
                }
            }
        }
    }
}
