package com.a5.flightbooking.model

data class Passenger(
    val name: String,
    val citizenship: String,
    val email: String = ""
)
