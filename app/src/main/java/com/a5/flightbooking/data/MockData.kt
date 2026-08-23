package com.a5.flightbooking.data

import com.a5.flightbooking.model.Flight
import com.a5.flightbooking.model.Passenger

object MockData {
    val flights = listOf(
        Flight(
            id = "f1",
            airline = "Angkor Sky Airways",
            price = 40.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Siem Reap", toCode = "SAI",
            departureDate = "10 June 2026",
            departureTime = "02:00 PM",
            arrivalTime = "02:55 PM",
            durationMinutes = 55
        ),
        Flight(
            id = "f2",
            airline = "AirAsia Cambodia",
            price = 75.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Hanoi", toCode = "HAN",
            departureDate = "10 June 2026",
            departureTime = "09:15 AM",
            arrivalTime = "10:00 AM",
            durationMinutes = 45
        ),
        Flight(
            id = "f3",
            airline = "Cambodia Airways",
            price = 80.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Ho Chi Minh City", toCode = "SGN",
            departureDate = "10 June 2026",
            departureTime = "06:30 AM",
            arrivalTime = "07:50 AM",
            durationMinutes = 80
        )
    )

    val pastFlights = listOf(
        Flight(
            id = "p1",
            airline = "Cambodia Airways",
            price = 75.0,
            fromCity = "Siem Reap", fromCode = "SAI",
            toCity = "Phnom Penh", toCode = "PNH",
            departureDate = "2 May 2026",
            departureTime = "11:00 AM",
            arrivalTime = "11:55 AM",
            durationMinutes = 55
        ),
        Flight(
            id = "p2",
            airline = "AirAsia Cambodia",
            price = 90.0,
            fromCity = "Ho Chi Minh City", fromCode = "SGN",
            toCity = "Phnom Penh", toCode = "PNH",
            departureDate = "18 April 2026",
            departureTime = "03:30 PM",
            arrivalTime = "04:15 PM",
            durationMinutes = 45
        )
    )

    val passenger = Passenger(
        name = "Sokha Ly",
        citizenship = "Cambodia",
        email = "sokha.ly@gmail.com"
    )
}
