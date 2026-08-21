package com.a5.flightbooking.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a5.flightbooking.ui.theme.DashedLine
import com.a5.flightbooking.ui.theme.NavyDark
import com.a5.flightbooking.ui.theme.TextPrimary
import com.a5.flightbooking.ui.theme.TextSecondary

// ─────────────────────────────────────────────
//  Top App Bar
// ─────────────────────────────────────────────

@Composable
fun FlightTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null,
    onTrailingClick: (() -> Unit)? = null,
    backgroundColor: Color = NavyDark
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }

        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        if (trailingIcon != null && onTrailingClick != null) {
            IconButton(
                onClick = onTrailingClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f))
            ) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}

// ─────────────────────────────────────────────
//  Flight Route Row  (RRP ---✈---> TNA)
// ─────────────────────────────────────────────

@Composable
fun FlightRouteRow(
    fromCode: String,
    toCode: String,
    fromLabel: String = "Canada",
    toLabel: String = "Mexico",
    date: String = "10 June 2023",
    duration: String = "2 hour 50 min",
    codeColor: Color = TextPrimary,
    labelColor: Color = TextSecondary,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(fromLabel, color = labelColor, fontSize = 11.sp)
            Text(toLabel, color = labelColor, fontSize = 11.sp)
        }
        Spacer(Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(fromCode, color = codeColor, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(8.dp))
            DashedFlightLine(modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            Text(toCode, color = codeColor, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(date, color = labelColor, fontSize = 11.sp)
            Text(duration, color = labelColor, fontSize = 11.sp)
            Text(date, color = labelColor, fontSize = 11.sp)
        }
    }
}

@Composable
fun DashedFlightLine(
    modifier: Modifier = Modifier,
    color: Color = DashedLine
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.weight(1f).height(1.dp)) {
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f), 0f)
            )
        }
        Icon(
            imageVector = Icons.Default.Flight,
            contentDescription = null,
            tint = NavyDark,
            modifier = Modifier.size(16.dp)
        )
        Canvas(modifier = Modifier.weight(1f).height(1.dp)) {
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f), 0f)
            )
        }
    }
}

// ─────────────────────────────────────────────
//  Primary Button
// ─────────────────────────────────────────────

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = NavyDark,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ─────────────────────────────────────────────
//  Bottom Navigation Bar
// ─────────────────────────────────────────────

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun FlightBottomNav(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("Ticket", Icons.Default.ConfirmationNumber, "my_tickets"),
        BottomNavItem("History", Icons.Default.History, "history"),
        BottomNavItem("Settings", Icons.Default.Settings, "settings")
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedRoute == item.route,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (selectedRoute == item.route)
                            FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavyDark,
                    selectedTextColor = NavyDark,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary
                )
            )
        }
    }
}

// ─────────────────────────────────────────────
//  Ticket Card
// ─────────────────────────────────────────────

@Composable
fun TicketCard(
    airlineName: String,
    price: String,
    fromCode: String,
    toCode: String,
    fromLabel: String = "Phnom Penh",
    toLabel: String = "Hanoi",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
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
                Text(text = airlineName, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text(text = price, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            }
            Spacer(Modifier.height(12.dp))
            FlightRouteRow(
                fromCode = fromCode,
                toCode = toCode,
                fromLabel = fromLabel,
                toLabel = toLabel
            )
        }
    }
}
