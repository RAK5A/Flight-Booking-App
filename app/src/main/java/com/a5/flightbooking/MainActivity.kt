package com.a5.flightbooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.a5.flightbooking.ui.theme.FlightBookingTheme
import com.example.flightapp.navigation.FlightAppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightBookingTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FlightAppNavHost()
                }
            }
        }
    }
}
