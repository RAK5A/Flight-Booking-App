package com.a5.flightbooking.ui.screens.payment

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.data.MockData
import com.a5.flightbooking.ui.components.FlightRouteRow
import com.a5.flightbooking.ui.components.FlightTopBar
import com.a5.flightbooking.ui.components.PrimaryButton
import com.a5.flightbooking.ui.theme.BackgroundLight
import com.a5.flightbooking.ui.theme.BorderLight
import com.a5.flightbooking.ui.theme.TextPrimary
import com.a5.flightbooking.ui.theme.TextSecondary

@Composable
fun PaymentScreen(
    onBack: () -> Unit,
    onPaySuccess: () -> Unit
) {
    val flight = MockData.flights.first()

    Column(modifier = Modifier.fillMaxSize().background(BackgroundLight)) {
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
                        duration = "${flight.durationMinutes} min"
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            Text("Payment method", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Spacer(Modifier.height(12.dp))

            PaymentMethodCard(icon = { MastercardIcon() }, cardNumber = "9876 1234 3456 4321")
            Spacer(Modifier.height(10.dp))
            PaymentMethodCard(icon = { ApplePayIcon() }, cardNumber = "3214 1234 3456 9274")
            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.5.dp, BorderLight, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Add New Method", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(20.dp))
            Text("Payment details", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PaymentDetailRow("Adult(2)", "\$1100", muted = true)
                    Spacer(Modifier.height(6.dp))
                    PaymentDetailRow("Children(2)", "\$1100", muted = true)
                    Spacer(Modifier.height(6.dp))
                    PaymentDetailRow("Tax", "\$100", muted = true)
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider(color = BorderLight)
                    Spacer(Modifier.height(12.dp))
                    PaymentDetailRow("Total payment", "\$2300", muted = false)
                }
            }

            Spacer(Modifier.height(16.dp))
            Text(
                "Total amount to pay \$2300",
                color = TextSecondary,
                fontSize = 13.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
        }

        Box(modifier = Modifier.fillMaxWidth().background(Color.White).padding(20.dp)) {
            PrimaryButton("Pay Now", onClick = onPaySuccess)
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
                .clip(CircleShape)
                .background(Color(0xFFEB001B).copy(alpha = 0.9f))
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .offset(x = (-10).dp)
                .clip(CircleShape)
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

@Composable
@Preview
fun PaymentScreenPreview() {
    PaymentScreen(
        onBack = {}
    ) { }
}
