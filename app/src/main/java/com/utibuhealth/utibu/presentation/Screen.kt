package com.utibuhealth.utibu.presentation

sealed class Screen(val route: String) {
    object RegisterScreen: Screen("register_screen")
    object PlaceOrderScreen: Screen("place_order_screen")

}