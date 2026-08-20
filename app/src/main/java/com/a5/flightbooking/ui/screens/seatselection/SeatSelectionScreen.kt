package com.example.flightapp.screens.seatselection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.a5.flightbooking.data.MockData

// Owner: Person C - this is the trickiest state logic in the app.
// TODO: render MockData.seatMap() as a 4-column grid. Track selected seat(s) with
// remember { mutableStateOf(...) }, toggle on tap (skip UNAVAILABLE seats), then
// call onCheckout() when the user taps Checkout.
@Composable
fun SeatSelectionScreen(
    flightId: String,
    onCheckout: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Seat selection - TODO Person C")
        Text("${MockData.seatMap().size} seats generated for flight $flightId")
        Button(onClick = onCheckout) {
            Text("Checkout")
        }
    }
}
