package com.utibuhealth.utibu

import RegisterScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utibuhealth.utibu.presentation.Screen
import com.utibuhealth.utibu.presentation.Screen.RegisterScreen.route
import com.utibuhealth.utibu.presentation.place_order.PlaceOrderScreen
//import com.utibuhealth.utibu.presentation.registration.RegisterScreen
import com.utibuhealth.utibu.presentation.ui.theme.UtibuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UtibuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.RegisterScreen.route
                    ) {
                        composable(
                            route = Screen.RegisterScreen.route
                        ) {
                            RegisterScreen(navController = navController)
                        }


                        composable(
                            route = Screen.PlaceOrderScreen.route
                        ) {
                            PlaceOrderScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

