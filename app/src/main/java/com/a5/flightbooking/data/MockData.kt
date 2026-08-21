package com.a5.flightbooking.data

import com.a5.flightbooking.model.Flight
import com.a5.flightbooking.model.Passenger

object MockData {
    val flights = listOf(
        Flight(
            id = "f1",
            airline = "Angkor Sky Airways",
            price = 85.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Siem Reap", toCode = "REP",
            departureDate = "10 June 2026",
            departureTime = "02:00 PM",
            arrivalTime = "02:55 PM",
            durationMinutes = 55
        ),
        Flight(
            id = "f2",
            airline = "Mekong Air",
            price = 95.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Sihanoukville", toCode = "KOS",
            departureDate = "10 June 2026",
            departureTime = "09:15 AM",
            arrivalTime = "10:00 AM",
            durationMinutes = 45
        ),
        Flight(
            id = "f3",
            airline = "Tonle Sap Airlines",
            price = 180.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Bangkok", toCode = "BKK",
            departureDate = "10 June 2026",
            departureTime = "06:30 AM",
            arrivalTime = "07:50 AM",
            durationMinutes = 80
        )
    )

    val passenger = Passenger(
        name = "Sokha Ly",
        citizenship = "Cambodia",
        email = "sokha.ly@gmail.com"
    )
}
