package com.a5.flightbooking.data

import com.a5.flightbooking.model.Flight
import com.a5.flightbooking.model.Passenger
import com.a5.flightbooking.model.Seat
import com.a5.flightbooking.model.SeatStatus
import com.a5.flightbooking.model.Ticket

object MockData {

    val flights = listOf(
        Flight(
            id = "f1",
            airline = "Air Cambodia",
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
            airline = "AirAsia Cambodia",
            price = 95.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Hanoi", toCode = "HAN",
            departureDate = "10 June 2026",
            departureTime = "09:15 AM",
            arrivalTime = "10:00 AM",
            durationMinutes = 45
        ),
        Flight(
            id = "f3",
            airline = "Sky Angkor Airlines",
            price = 180.0,
            fromCity = "Phnom Penh", fromCode = "PNH",
            toCity = "Bangkok", toCode = "BKK",
            departureDate = "10 June 2026",
            departureTime = "06:30 AM",
            arrivalTime = "07:50 AM",
            durationMinutes = 80
        )
    )

    fun flightById(id: String): Flight = flights.first { it.id == id }

    // Generates a 6-row x 4-column seat map (A-D), a few pre-marked unavailable
    fun seatMap(): List<Seat> {
        val unavailable = setOf("A2", "D2", "B4", "C4")
        val columns = listOf("A", "B", "C", "D")
        val seats = mutableListOf<Seat>()
        for (row in 1..6) {
            for (col in columns) {
                val id = "$col$row"
                seats.add(
                    Seat(
                        id = id,
                        row = row,
                        column = col,
                        isBusinessClass = row <= 3,
                        status = if (id in unavailable) SeatStatus.UNAVAILABLE else SeatStatus.AVAILABLE
                    )
                )
            }
        }
        return seats
    }

    // Called after "Pay Now" — builds a fake ticket instead of hitting a real payment API
    fun createMockTicket(flightId: String, seat: Seat?): Ticket {
        return Ticket(
            id = "t-$flightId",
            flight = flightById(flightId),
            passenger = Passenger(name = "Sokha Ly", citizenship = "Cambodia"),
            seat = seat,
            gate = "A4",
            terminal = "B3"
        )
    }
}
