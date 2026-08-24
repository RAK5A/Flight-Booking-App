package com.example.flightapp.screens.seatselection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.model.Seat
import com.a5.flightbooking.model.SeatStatus
import com.a5.flightbooking.ui.components.*
import com.a5.flightbooking.ui.theme.*

@Composable
fun SeatSelectionScreen(
    flightId: String,
    onCheckout: (seatId: String) -> Unit,
    onBack: () -> Unit = {}
) {
    val seats = remember { MockData.seatMap() }
    var selectedSeat by remember { mutableStateOf<Seat?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        FlightTopBar(
            title = "Select Your Seat",
            onBack = onBack,
            trailingIcon = Icons.Default.Refresh,
            onTrailingClick = { selectedSeat = null }
        )

        // Legend
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SeatLegendItem(color = NavyDark, label = "Selected")
            SeatLegendItem(color = Color.White, label = "Available", bordered = true)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp), tint = TextSecondary)
                }
                Spacer(Modifier.width(4.dp))
                Text("Unavailable", fontSize = 12.sp, color = TextSecondary)
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Seat Selection",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextPrimary
                        )

                        Spacer(Modifier.height(16.dp))

                        // Column headers
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.width(40.dp))
                            listOf("A", "B", "C", "D").forEach { col ->
                                Text(
                                    col,
                                    modifier = Modifier.weight(1f),
                                    fontSize = 12.sp,
                                    color = TextSecondary,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        // Seat grid
                        val rows = seats.groupBy { it.row }
                        rows.forEach { (row, rowSeats) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    row.toString(),
                                    modifier = Modifier.width(40.dp),
                                    fontSize = 13.sp,
                                    color = TextSecondary,
                                    fontWeight = FontWeight.Medium
                                )
                                rowSeats.sortedBy { it.column }.forEach { seat ->
                                    val isSelected = selectedSeat?.id == seat.id
                                    val isUnavailable = seat.status == SeatStatus.UNAVAILABLE

                                    SeatButton(
                                        seatId = seat.id,
                                        isSelected = isSelected,
                                        isUnavailable = isUnavailable,
                                        modifier = Modifier.weight(1f).padding(4.dp),
                                        onClick = {
                                            if (!isUnavailable) {
                                                selectedSeat = if (isSelected) null else seat
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            PrimaryButton(
                text = "Checkout",
                onClick = {
                    selectedSeat?.let { onCheckout(it.id) }
                },
                enabled = selectedSeat != null
            )
        }
    }
}

@Composable
fun SeatButton(
    seatId: String,
    isSelected: Boolean,
    isUnavailable: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bgColor = when {
        isSelected -> NavyDark
        isUnavailable -> SeatAvailable
        else -> SeatAvailable
    }
    val textColor = if (isSelected) Color.White else TextSecondary

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .then(
                if (!isSelected && !isUnavailable)
                    Modifier.border(1.dp, BorderLight, RoundedCornerShape(8.dp))
                else Modifier
            )
            .clickable(enabled = !isUnavailable) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isUnavailable) {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(16.dp)
            )
        } else {
            Text(seatId, fontSize = 11.sp, color = textColor, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun SeatLegendItem(color: Color, label: String, bordered: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color)
                .then(if (bordered) Modifier.border(1.dp, BorderLight, RoundedCornerShape(4.dp)) else Modifier)
        )
        Spacer(Modifier.width(6.dp))
        Text(label, fontSize = 12.sp, color = TextSecondary)
    }
}
