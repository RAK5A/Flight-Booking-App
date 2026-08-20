package com.a5.flightbooking.model

data class Ticket(
    val id: String,
    val flight: Flight,
    val passenger: Passenger,
    val seat: Seat?,
    val gate: String,
    val terminal: String
)
