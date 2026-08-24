package com.example.flightapp.screens.boardingpass

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.ui.components.*
import com.a5.flightbooking.ui.theme.*

@Composable
fun BoardingPassScreen(
    ticketId: String,
    onBack: () -> Unit = {}
) {
    val ticket = MockData.ticketById(ticketId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        FlightTopBar(
            title = "Boarding Pass",
            onBack = onBack,
            trailingIcon = Icons.Default.CenterFocusWeak,
            onTrailingClick = {}
        )

        if (ticket == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ticket not found")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                // Main boarding pass card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        // Airline & flight number
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                ticket.flight.airline,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                "A5-${ticket.flight.id.takeLast(3)}",
                                fontSize = 14.sp,
                                color = TextSecondary
                            )
                        }

                        Spacer(Modifier.height(16.dp))
                        HorizontalDivider(color = BorderLight)
                        Spacer(Modifier.height(16.dp))

                        FlightRouteRow(
                            fromCode = ticket.flight.fromCode,
                            toCode = ticket.flight.toCode,
                            fromLabel = ticket.flight.fromCity,
                            toLabel = ticket.flight.toCity,
                            date = ticket.flight.departureDate,
                            duration = "${ticket.flight.durationMinutes / 60} hour ${ticket.flight.durationMinutes % 60} min"
                        )

                        Spacer(Modifier.height(16.dp))
                        HorizontalDivider(color = BorderLight)
                        Spacer(Modifier.height(16.dp))

                        // Passenger details
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            InfoBlock(label = "Passenger Name", value = ticket.passenger.name)
                            InfoBlock(label = "Citizenship", value = ticket.passenger.citizenship, align = Alignment.End)
                        }

                        Spacer(Modifier.height(16.dp))

                        // Departure / Arrival
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            InfoBlock(label = "Departure", value = ticket.flight.departureTime, modifier = Modifier.weight(1f))
                            InfoBlock(label = "Arrival", value = ticket.flight.arrivalTime, modifier = Modifier.weight(1f))
                        }

                        Spacer(Modifier.height(12.dp))

                        // Seat / Terminal / Gate
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            InfoBlock(label = "Seat", value = ticket.seat?.id ?: "N/A", modifier = Modifier.weight(1f))
                            InfoBlock(label = "Terminal", value = ticket.terminal, modifier = Modifier.weight(1f))
                            InfoBlock(label = "Gate", value = ticket.gate, modifier = Modifier.weight(1f))
                        }

                        Spacer(Modifier.height(20.dp))

                        // Ticket cut divider
                        TicketCutDivider()

                        Spacer(Modifier.height(16.dp))

                        // Barcode section
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Submit at registration",
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                            Spacer(Modifier.height(12.dp))
                            BarcodeView(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                            )
                        }
                    }
                }
            }

            // Download button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                PrimaryButton("Download Ticket", onClick = {})
            }
        }
    }
}

@Composable
fun InfoBlock(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    align: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = align) {
        Text(label, color = TextSecondary, fontSize = 11.sp)
        Spacer(Modifier.height(2.dp))
        Text(value, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun TicketCutDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset(x = (-12).dp)
                .clip(CircleShape)
                .background(BackgroundLight)
        )
        Canvas(modifier = Modifier.weight(1f).height(1.dp)) {
            drawLine(
                color = DashedLine,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f)
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset(x = 12.dp)
                .clip(CircleShape)
                .background(BackgroundLight)
        )
    }
}

@Composable
fun BarcodeView(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val barWidth = size.width / 80f
        val barHeights = listOf(
            1f, 0f, 1f, 1f, 0f, 1f, 0f, 0f, 1f, 0f,
            1f, 1f, 0f, 1f, 1f, 0f, 1f, 0f, 1f, 1f,
            0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f, 0f, 1f,
            1f, 0f, 0f, 1f, 0f, 1f, 1f, 0f, 1f, 0f,
            1f, 1f, 0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f,
            0f, 1f, 1f, 0f, 1f, 0f, 1f, 1f, 0f, 1f,
            1f, 0f, 1f, 0f, 0f, 1f, 1f, 0f, 1f, 0f,
            1f, 1f, 0f, 0f, 1f, 1f, 0f, 1f, 0f, 1f
        )
        barHeights.forEachIndexed { index, h ->
            if (h == 1f) {
                drawRect(
                    color = Color(0xFF1A2340),
                    topLeft = Offset(index * barWidth, 0f),
                    size = androidx.compose.ui.geometry.Size(barWidth * 0.7f, size.height)
                )
            }
        }
    }
}
