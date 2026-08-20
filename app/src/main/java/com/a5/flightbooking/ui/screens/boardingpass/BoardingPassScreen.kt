package com.example.flightapp.screens.boardingpass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Owner: Person B
// TODO: look up the Ticket by ticketId (MockData.createMockTicket already builds one at
// payment time - simplest option is to pass the built Ticket via a shared ViewModel
// instead of re-deriving it from just the ID) and render flight/passenger/seat/gate + barcode
@Composable
fun BoardingPassScreen(ticketId: String) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Boarding pass - TODO Person B")
        Text("Ticket ID: $ticketId")
    }
}
