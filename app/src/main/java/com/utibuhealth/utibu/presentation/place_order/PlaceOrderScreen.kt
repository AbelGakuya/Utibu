package com.utibuhealth.utibu.presentation.place_order

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.utibuhealth.utibu.common.Resource
import com.utibuhealth.utibu.data.remote.Order
import java.util.Date

@Composable
fun PlaceOrderScreen(
    navController: NavController,
    viewModel: PlaceOrderViewModel = hiltViewModel()
) {
    var userId by remember { mutableStateOf(0) }
    var medicationId by remember { mutableStateOf(0) }
    var quantity by remember { mutableStateOf(0) }

    // Background gradient
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF64B5F6),
                        Color(0xFF0D47A1)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App logo or header
            Text(
                text = "Utibu Health",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // User ID input field
            OutlinedTextField(
                value = userId.toString(),
                onValueChange = { userId = it.toIntOrNull() ?: 0 },
                label = { Text("User ID", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            // Medication ID input field
            OutlinedTextField(
                value = medicationId.toString(),
                onValueChange = { medicationId = it.toIntOrNull() ?: 0 },
                label = { Text("Medication ID", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            // Quantity input field
            OutlinedTextField(
                value = quantity.toString(),
                onValueChange = { quantity = it.toIntOrNull() ?: 0 },
                label = { Text("Quantity", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )

            // Place Order button
            Button(
                onClick = {
                    val order = Order(
                        orderId = 0, // Assuming order ID will be assigned by the server
                        userId = userId,
                        medicationId = medicationId,
                        quantity = quantity,
                        orderDate = Date(), // You may need to use a proper date
                        status = "Placed" // Assuming the initial status is "Placed"
                    )
                    viewModel.placeOrder(order)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(
                    "Place Order",
                    color = Color.Blue
                )
            }
        }
    }

    // Observe the place order state
    viewModel.placeOrderState.observeAsState().value?.let { placeOrderState ->


        when (placeOrderState) {
            is PlaceOrderViewState.Loading -> {
                // Show a loading indicator or progress bar
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Add padding to center the indicator
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp) // Set the desired size for the indicator
                    )
                }
            }

            is PlaceOrderViewState.Success -> {
                // Order placed successfully
                // You may navigate to a different screen or show a confirmation message
                // For example:
                // navController.navigate("order_confirmation/${placeOrderState.data.orderId}")
            }

            is PlaceOrderViewState.Error -> {
                // Show an error message
                val errorMessage = placeOrderState.message
                 //   (placeOrderState as Resource.Error<Order>).message ?: "Failed to place order"
                // Display the error message to the user
                Toast.makeText(
                    LocalContext.current,
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}
