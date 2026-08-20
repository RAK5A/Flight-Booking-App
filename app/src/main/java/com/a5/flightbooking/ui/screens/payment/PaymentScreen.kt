package com.a5.flightbooking.ui.screens.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.a5.flightbooking.data.MockData

// Owner: Person C
// TODO: render 2 hardcoded "saved cards" + a payment summary. On "Pay Now", DON'T call a
// real payment API - just call MockData.createMockTicket(flightId, selectedSeat) and pass
// the resulting ticket id into onPaySuccess. This is the feature we're deliberately faking
// to stay in scope for 2 weeks.
@Composable
fun PaymentScreen(
    flightId: String,
    onPaySuccess: (ticketId: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Payment (mocked) - TODO Person C")
        Button(onClick = {
            val ticket = MockData.createMockTicket(flightId, seat = null)
            onPaySuccess(ticket.id)
        }) {
            Text("Pay Now")
        }
    }
}
