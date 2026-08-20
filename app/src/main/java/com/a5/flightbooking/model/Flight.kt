package com.a5.flightbooking.model

data class Flight(
    val id: String,
    val airline: String,
    val price: Double,
    val fromCity: String,
    val fromCode: String,
    val toCity: String,
    val toCode: String,
    val departureDate: String,
    val departureTime: String,
    val arrivalTime: String,
    val durationMinutes: Int
)
