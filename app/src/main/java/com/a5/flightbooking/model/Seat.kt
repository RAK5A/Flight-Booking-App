package com.a5.flightbooking.model

enum class SeatStatus { AVAILABLE, SELECTED, UNAVAILABLE }

data class Seat(
    val id: String,        // e.g. "A2"
    val row: Int,
    val column: String,    // "A".."D"
    val isBusinessClass: Boolean,
    val status: SeatStatus
)
