package com.example.flightapp.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Owner: Person A
// TODO: build the "Explore Exciting Destinations" onboarding screen
@Composable
fun OnboardingScreen(onGetStarted: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Onboarding screen - TODO Person A")
        Button(onClick = onGetStarted) {
            Text("Get Started")
        }
    }
}
