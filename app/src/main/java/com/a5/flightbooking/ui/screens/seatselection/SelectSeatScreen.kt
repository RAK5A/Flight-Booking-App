package com.a5.flightbooking.ui.screens.seatselection

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.ui.components.FlightTopBar
import com.a5.flightbooking.ui.components.PrimaryButton
import com.a5.flightbooking.ui.theme.BackgroundLight
import com.a5.flightbooking.ui.theme.BorderLight
import com.a5.flightbooking.ui.theme.NavyDark
import com.a5.flightbooking.ui.theme.SeatAvailable
import com.a5.flightbooking.ui.theme.TextPrimary
import com.a5.flightbooking.ui.theme.TextSecondary

enum class SeatStatus { AVAILABLE, SELECTED, UNAVAILABLE }

data class SeatData(val id: String, var status: SeatStatus)

@Composable
fun SelectSeatScreen(
    onBack: () -> Unit,
    onCheckout: () -> Unit
) {
    val rows = listOf("A", "B", "C", "D", "E", "F")
    val cols = listOf("1", "2", "3", "4")

    val preSelected = setOf("A2", "D2", "B4", "C4")
    val unavailable = setOf<String>()

    var selectedSeats by remember {
        mutableStateOf(preSelected.toMutableSet())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        FlightTopBar(
            title = "Select Your Seat",
            onBack = onBack,
            trailingIcon = Icons.Default.Refresh,
            onTrailingClick = { selectedSeats = preSelected.toMutableSet() }
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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Business Class",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextPrimary
                            )
                            Row {
                                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = TextSecondary)
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = TextSecondary)
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        // Column headers
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.width(40.dp))
                            cols.forEach { col ->
                                Text(
                                    col,
                                    modifier = Modifier.weight(1f),
                                    fontSize = 12.sp,
                                    color = TextSecondary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        // Seat grid
                        rows.forEach { row ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    row,
                                    modifier = Modifier.width(40.dp),
                                    fontSize = 13.sp,
                                    color = TextSecondary,
                                    fontWeight = FontWeight.Medium
                                )
                                cols.forEach { col ->
                                    val seatId = "$row$col"
                                    val isSelected = selectedSeats.contains(seatId)
                                    val isUnavailable = unavailable.contains(seatId)

                                    SeatButton(
                                        seatId = seatId,
                                        isSelected = isSelected,
                                        isUnavailable = isUnavailable,
                                        modifier = Modifier.weight(1f).padding(4.dp),
                                        onClick = {
                                            if (!isUnavailable) {
                                                val newSet = selectedSeats.toMutableSet()
                                                if (isSelected) newSet.remove(seatId)
                                                else newSet.add(seatId)
                                                selectedSeats = newSet
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
            PrimaryButton("Checkout", onClick = onCheckout)
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
