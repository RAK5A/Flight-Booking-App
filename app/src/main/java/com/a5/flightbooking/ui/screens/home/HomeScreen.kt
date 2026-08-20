package com.example.flightapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Owner: Person A
// TODO: build the search form (From/To/Departure/Return/Travelers) + upcoming flights preview
@Composable
fun HomeScreen(
    onSearch: () -> Unit,
    onOpenTicket: (flightId: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Home / search screen - TODO Person A")
        Button(onClick = onSearch) {
            Text("Search")
        }
    }
}
