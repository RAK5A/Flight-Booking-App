package com.example.flightapp.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.ui.components.*
import com.a5.flightbooking.ui.theme.BackgroundLight

@Composable
fun SearchResultsScreen(
    onSelectFlight: (flightId: String) -> Unit,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        FlightTopBar(
            title = "Search Results",
            onBack = onBack,
            trailingIcon = Icons.Default.FilterList,
            onTrailingClick = {}
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(MockData.flights) { flight ->
                TicketCard(
                    airlineName = flight.airline,
                    price = "$${flight.price.toInt()}",
                    fromCode = flight.fromCode,
                    toCode = flight.toCode,
                    onClick = { onSelectFlight(flight.id) }
                )
            }
        }
    }
}
