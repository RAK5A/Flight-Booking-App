package com.a5.flightbooking.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.ui.components.FlightBottomNav
import com.a5.flightbooking.ui.components.TicketCard
import com.a5.flightbooking.ui.theme.BackgroundLight
import com.a5.flightbooking.ui.theme.NavyDark

@Composable
fun HistoryScreen(
    onHomeClick: () -> Unit,
    onTicketClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var selectedRoute by remember { mutableStateOf("history") }

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NavyDark)
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 20.dp)
        ) {
            Text(
                "Flight History",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(MockData.pastFlights) { flight ->
                TicketCard(
                    airlineName = flight.airline,
                    price = "$${flight.price.toInt()}",
                    fromCode = flight.fromCode,
                    toCode = flight.toCode,
                    fromLabel = flight.fromCity,
                    toLabel = flight.toCity,
                    onClick = {} // past flights aren't tappable into the booking flow
                )
            }
        }

        FlightBottomNav(
            selectedRoute = selectedRoute,
            onItemSelected = { route ->
                selectedRoute = route
                when (route) {
                    "home" -> onHomeClick()
                    "my_tickets" -> onTicketClick()
                    "settings" -> onProfileClick()
                }
            }
        )
    }
}

@Composable
@Preview
fun HistoryScreenPreview() {
    HistoryScreen(
        onHomeClick = {},
        onTicketClick = {}
    ) { }
}
