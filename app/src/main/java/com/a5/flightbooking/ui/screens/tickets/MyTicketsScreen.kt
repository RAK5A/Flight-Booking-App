package com.a5.flightbooking.ui.screens.tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.data.Data
import com.a5.flightbooking.ui.components.FlightBottomNav
import com.a5.flightbooking.ui.components.FlightRouteRow
import com.a5.flightbooking.ui.components.TicketCard
import com.a5.flightbooking.ui.theme.BackgroundLight
import com.a5.flightbooking.ui.theme.NavyDark

@Composable
fun MyTicketsScreen(
    onBack: () -> Unit,
    onTicketClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    var selectedRoute by remember { mutableStateOf("my_tickets") }
    val firstFlight = Data.flights.first()

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NavyDark)
                .padding(horizontal = 20.dp)
                .padding(top = 48.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(44.dp).clip(CircleShape).background(Color(0xFF8B9DC3)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                }
                Text("My Ticket", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.15f))
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                }
            }
            Spacer(Modifier.height(20.dp))
            FlightRouteRow(
                fromCode = firstFlight.fromCode,
                toCode = firstFlight.toCode,
                fromLabel = firstFlight.fromCity,
                toLabel = firstFlight.toCity,
                date = firstFlight.departureDate,
                duration = "${firstFlight.durationMinutes} min",
                codeColor = Color.White,
                labelColor = Color.White.copy(alpha = 0.6f)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(Data.flights) { flight ->
                TicketCard(
                    airlineName = flight.airline,
                    price = "$${flight.price.toInt()}",
                    fromCode = flight.fromCode,
                    toCode = flight.toCode,
                    fromLabel = flight.fromCity,
                    toLabel = flight.toCity,
                    onClick = onTicketClick
                )
            }
        }

        FlightBottomNav(
            selectedRoute = selectedRoute,
            onItemSelected = { route ->
                selectedRoute = route
                when (route) {
                    "home" -> onHomeClick()
                    "settings" -> onProfileClick()
                }
            }
        )
    }
}