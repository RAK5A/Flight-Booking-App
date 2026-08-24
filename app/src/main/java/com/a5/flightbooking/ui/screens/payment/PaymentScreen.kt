package com.example.flightapp.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.ui.components.*
import com.a5.flightbooking.ui.theme.*

@Composable
fun PaymentScreen(
    flightId: String,
    seatId: String,
    onPaySuccess: (ticketId: String) -> Unit,
    onBack: () -> Unit = {}
) {
    val flight = MockData.flightById(flightId)
    val seat = MockData.seatMap().find { it.id == seatId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        FlightTopBar(
            title = "Payment Method",
            onBack = onBack,
            trailingIcon = Icons.Default.MoreVert,
            onTrailingClick = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            // Flight route summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    FlightRouteRow(
                        fromCode = flight.fromCode,
                        toCode = flight.toCode,
                        fromLabel = flight.fromCity,
                        toLabel = flight.toCity,
                        date = flight.departureDate,
                        duration = "${flight.durationMinutes / 60} hour ${flight.durationMinutes % 60} min"
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Payment Method section
            Text(
                "Payment method",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(12.dp))

            // Mastercard
            PaymentMethodCard(
                icon = { MastercardIcon() },
                cardNumber = "9876 1234 3456 4321"
            )
            Spacer(Modifier.height(10.dp))

            // Apple Pay
            PaymentMethodCard(
                icon = { ApplePayIcon() },
                cardNumber = "3214 1234 3456 9274"
            )
            Spacer(Modifier.height(10.dp))

            // Add new method
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.5.dp, BorderLight, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Add New Method",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(Modifier.height(20.dp))

            // Payment details
            Text(
                "Payment details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PaymentDetailRow("Adult(1)", "$${flight.price.toInt()}", muted = true)
                    Spacer(Modifier.height(6.dp))
                    PaymentDetailRow("Seat Fee (${seatId})", if (seat?.isBusinessClass == true) "$100" else "$0", muted = true)
                    Spacer(Modifier.height(6.dp))
                    PaymentDetailRow("Tax", "$50", muted = true)
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = BorderLight)
                    Spacer(Modifier.height(12.dp))
                    val total = flight.price.toInt() + (if (seat?.isBusinessClass == true) 100 else 0) + 50
                    PaymentDetailRow("Total payment", "$$total", muted = false)
                }
            }

            Spacer(Modifier.height(16.dp))
            val finalTotal = flight.price.toInt() + (if (seat?.isBusinessClass == true) 100 else 0) + 50
            Text(
                "Total amount to pay $$finalTotal",
                color = TextSecondary,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
        ) {
            PrimaryButton("Pay Now", onClick = {
                val ticket = MockData.createMockTicket(flightId, seat)
                onPaySuccess(ticket.id)
            })
        }
    }
}

@Composable
fun PaymentMethodCard(
    icon: @Composable () -> Unit,
    cardNumber: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Spacer(Modifier.width(12.dp))
            Text(
                cardNumber,
                fontSize = 14.sp,
                color = TextPrimary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun MastercardIcon() {
    Row {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(Color(0xFFEB001B).copy(alpha = 0.9f))
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .offset(x = (-10).dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(Color(0xFFF79E1B).copy(alpha = 0.9f))
        )
    }
}

@Composable
fun ApplePayIcon() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.CreditCard,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun PaymentDetailRow(label: String, amount: String, muted: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontSize = 14.sp,
            color = if (muted) TextSecondary else TextPrimary,
            fontWeight = if (muted) FontWeight.Normal else FontWeight.SemiBold
        )
        Text(
            amount,
            fontSize = 14.sp,
            color = if (muted) TextSecondary else TextPrimary,
            fontWeight = if (muted) FontWeight.Normal else FontWeight.SemiBold
        )
    }
}
