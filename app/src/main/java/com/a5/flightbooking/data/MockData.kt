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
            airline = "Canada Airways",
            price = 550.0,
            fromCity = "Canada", fromCode = "RRP",
            toCity = "Mexico", toCode = "TNA",
            departureDate = "10 June 2023",
            departureTime = "02:00 PM",
            arrivalTime = "04:50 PM",
            durationMinutes = 170
        ),
        Flight(
            id = "f2",
            airline = "Sky Glide Airlines",
            price = 650.0,
            fromCity = "Canada", fromCode = "CCP",
            toCity = "Mexico", toCode = "RFS",
            departureDate = "10 June 2023",
            departureTime = "09:15 AM",
            arrivalTime = "12:05 PM",
            durationMinutes = 170
        ),
        Flight(
            id = "f3",
            airline = "Stellar Airways",
            price = 500.0,
            fromCity = "Canada", fromCode = "RHP",
            toCity = "Mexico", toCode = "QTR",
            departureDate = "10 June 2023",
            departureTime = "06:30 AM",
            arrivalTime = "09:20 AM",
            durationMinutes = 170
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
            passenger = Passenger(name = "Mahmudul Hasan", citizenship = "Canada"),
            seat = seat,
            gate = "A4",
            terminal = "B3"
        )
    }
}
