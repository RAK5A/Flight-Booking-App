package com.example.flightapp.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.ui.components.FlightTopBar
import com.a5.flightbooking.ui.theme.*

data class NotificationItem(
    val title: String,
    val body: String,
    val time: String,
    val icon: ImageVector
)

@Composable
fun NotificationsScreen(onBack: () -> Unit) {
    val todayItems = listOf(
        NotificationItem(
            "Your booking has been successfully",
            "Thank you for booking your flight ticket through our app. Your booking has been successfully processed and your ticket has been issued.",
            "20 Jun 2023 • 08:30 PM",
            Icons.Default.ConfirmationNumber
        ),
        NotificationItem(
            "Don't miss out on discounted flights!",
            "Book now and save up to 50% on your next trip. Our app makes it easy to search and compare prices, so you can find the best deal in just a few.",
            "20 Jun 2023 • 08:30 PM",
            Icons.Default.LocalOffer
        )
    )

    val yesterdayItems = listOf(
        NotificationItem(
            "Tired of paying high prices for flights?",
            "Our app offers exclusive deals on popular routes. Check it out now!",
            "20 Jun 2023 • 08:30 PM",
            Icons.Default.Info
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        FlightTopBar(
            title = "Notification",
            onBack = onBack
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Today",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
            }
            items(todayItems.size) { index ->
                NotificationCard(todayItems[index])
            }

            item {
                Spacer(Modifier.height(4.dp))
                Text(
                    "Yesterday",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextPrimary
                )
            }
            items(yesterdayItems.size) { index ->
                NotificationCard(yesterdayItems[index])
            }
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BackgroundLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    item.icon,
                    contentDescription = null,
                    tint = NavyDark,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    item.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = TextPrimary
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    item.body,
                    fontSize = 12.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    item.time,
                    fontSize = 11.sp,
                    color = TextLight
                )
            }
        }
    }
}
