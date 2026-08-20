package com.example.flightapp.screens.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.a5.flightbooking.data.MockData

// Owner: Person B
// TODO: render MockData.flights as a LazyColumn list, each item tappable -> onSelectFlight(flight.id)
@Composable
fun SearchResultsScreen(
    onSelectFlight: (flightId: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Search results / ticket list - TODO Person B")
        Text("${MockData.flights.size} mock flights available")
    }
}
