package com.a5.flightbooking.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.ui.components.FlightBottomNav
import com.a5.flightbooking.ui.components.PrimaryButton
import com.a5.flightbooking.ui.components.TicketCard
import com.a5.flightbooking.ui.theme.BackgroundLight
import com.a5.flightbooking.ui.theme.BorderLight
import com.a5.flightbooking.ui.theme.NavyDark
import com.a5.flightbooking.ui.theme.TextPrimary
import com.a5.flightbooking.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    onTicketClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var selectedRoute by remember { mutableStateOf("home") }
    var travelers by remember { mutableIntStateOf(4) }
    val firstFlight = MockData.flights.first()

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
        // Header
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(44.dp).clip(CircleShape).background(Color(0xFF8B9DC3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                    }
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text("Good Morning", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                        Text(MockData.passenger.name, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.15f))
                ) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                }
            }
            Spacer(Modifier.height(20.dp))
            Text("Securely Book\nyour Flight Ticket", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 34.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height((-16).dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    SearchField(
                        label = "From",
                        value = "${firstFlight.fromCity} (${firstFlight.fromCode})",
                        icon = Icons.Default.FlightTakeoff
                    )
                    Spacer(Modifier.height(12.dp))
                    SearchField(
                        label = "To",
                        value = "${firstFlight.toCity} (${firstFlight.toCode})",
                        icon = Icons.Default.FlightLand
                    )
                    Spacer(Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        DateField(label = "Departure", value = firstFlight.departureDate, modifier = Modifier.weight(1f))
                        DateField(label = "Return", value = "12 June 2026", modifier = Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(12.dp))
                    Column {
                        Text("Travelers", color = TextSecondary, fontSize = 12.sp)
                        Spacer(Modifier.height(6.dp))
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "${travelers} Person",
                                color = TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { if (travelers > 1) travelers-- },
                                    modifier = Modifier.size(32.dp).clip(CircleShape).border(1.dp, BorderLight, CircleShape)
                                ) {
                                    Icon(Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(16.dp))
                                }
                                Text(
                                    text = String.format("%02d", travelers),
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                IconButton(
                                    onClick = { travelers++ },
                                    modifier = Modifier.size(32.dp).clip(CircleShape).background(NavyDark)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    PrimaryButton("Search", onClick = { onTicketClick() })
                }
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Upcoming flights", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = TextPrimary)
                Text("See All", color = TextSecondary, fontSize = 13.sp)
            }
            Spacer(Modifier.height(12.dp))

            MockData.flights.forEach { flight ->
                TicketCard(
                    airlineName = flight.airline,
                    price = "$${flight.price.toInt()}",
                    fromCode = flight.fromCode,
                    toCode = flight.toCode,
                    fromLabel = flight.fromCity,
                    toLabel = flight.toCity,
                    onClick = onTicketClick
                )
                Spacer(Modifier.height(12.dp))
            }
            Spacer(Modifier.height(8.dp))
        }

        FlightBottomNav(
            selectedRoute = selectedRoute,
            onItemSelected = { route ->
                selectedRoute = route
                when (route) {
                    "my_tickets" -> onTicketClick()
                    "settings" -> onProfileClick()
                }
            }
        )
    }
}

@Composable
fun SearchField(label: String, value: String, icon: ImageVector) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(BackgroundLight).padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(label, color = TextSecondary, fontSize = 11.sp)
                Text(value, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun DateField(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.clip(RoundedCornerShape(12.dp)).background(BackgroundLight).padding(12.dp)) {
        Text(label, color = TextSecondary, fontSize = 11.sp)
        Text(value, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}