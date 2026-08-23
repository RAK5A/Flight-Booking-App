package com.a5.flightbooking.ui.screens.boardingpass

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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.navigation.Screen
import com.a5.flightbooking.ui.components.FlightRouteRow
import com.a5.flightbooking.ui.components.FlightTopBar
import com.a5.flightbooking.ui.components.PrimaryButton
import com.a5.flightbooking.ui.theme.BackgroundLight
import com.a5.flightbooking.ui.theme.BorderLight
import com.a5.flightbooking.ui.theme.DashedLine
import com.a5.flightbooking.ui.theme.TextPrimary
import com.a5.flightbooking.ui.theme.TextSecondary

@Composable
fun BoardingPassScreen(onBack: () -> Unit) {
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
                            "Canada Airways",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            "A5-541",
                            fontSize = 14.sp,
                            color = TextSecondary
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider(color = BorderLight)
                    Spacer(Modifier.height(16.dp))

                    FlightRouteRow(
                        fromCode = "RRP",
                        toCode = "TNA",
                        fromLabel = "Canada",
                        toLabel = "Mexico",
                        date = "10 June 2023",
                        duration = "2 hour 50 min"
                    )

                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider(color = BorderLight)
                    Spacer(Modifier.height(16.dp))

                    // Passenger details
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InfoBlock(label = "Passenger Name", value = "Mahmudul Hasan")
                        InfoBlock(label = "Citizenship", value = "Canada", align = Alignment.End)
                    }

                    Spacer(Modifier.height(16.dp))

                    // Departure / Arrival
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InfoBlock(
                            label = "Departure",
                            value = "02:00 PM",
                            modifier = Modifier.weight(1f)
                        )
                        InfoBlock(
                            label = "Arrival",
                            value = "04:50 PM",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Seat / Terminal / Gate
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InfoBlock(label = "Seat", value = "3F", modifier = Modifier.weight(1f))
                        InfoBlock(label = "Terminal", value = "B3", modifier = Modifier.weight(1f))
                        InfoBlock(label = "Gate", value = "A4", modifier = Modifier.weight(1f))
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
        Canvas(modifier = Modifier
            .weight(1f)
            .height(1.dp)) {
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
                    size = Size(barWidth * 0.7f, size.height)
                )
            }
        }
    }
}

@Composable
@Preview
fun BoardingPassScreenPreview() {
    BoardingPassScreen() {

    }
}