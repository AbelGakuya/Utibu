package com.utibuhealth.utibu.presentation.place_order

import com.utibuhealth.utibu.data.remote.Order
import com.utibuhealth.utibu.data.remote.User

sealed class PlaceOrderViewState {
    object Loading : PlaceOrderViewState()
    data class Success(val order: Order) : PlaceOrderViewState()
    data class Error(val message: String) : PlaceOrderViewState()
}
