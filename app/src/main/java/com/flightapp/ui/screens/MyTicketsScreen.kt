package com.flightapp.ui.screens

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
import com.flightapp.ui.components.*
import com.flightapp.ui.theme.*

data class FlightTicket(
    val airline: String,
    val price: String,
    val from: String,
    val to: String
)

@Composable
fun MyTicketsScreen(
    onBack: () -> Unit,
    onTicketClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    var selectedRoute by remember { mutableStateOf("my_tickets") }

    val tickets = listOf(
        FlightTicket("Canada Airways", "\$550", "RRP", "TNA"),
        FlightTicket("Sky Glide Airlines", "\$650", "CCP", "RFS"),
        FlightTicket("Stellar Airways", "\$500", "RHP", "QTR"),
        FlightTicket("Pacific Star Airways", "\$450", "LGA", "DXB"),
        FlightTicket("Blue Horizon Air", "\$720", "JFK", "CDG")
    )

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        // Header with profile
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
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF8B9DC3)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                }

                Text(
                    "My Ticket",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Summary route in header
            FlightRouteRow(
                fromCode = "RRP",
                toCode = "TNA",
                fromLabel = "Canada",
                toLabel = "Mexico",
                date = "10 June 2023",
                duration = "2 hour 50 min",
                codeColor = Color.White,
                labelColor = Color.White.copy(alpha = 0.6f)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(tickets) { ticket ->
                TicketCard(
                    airlineName = ticket.airline,
                    price = ticket.price,
                    fromCode = ticket.from,
                    toCode = ticket.to,
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
