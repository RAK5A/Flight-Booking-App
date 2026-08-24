package com.a5.flightbooking.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object SearchResults : Screen("search_results")

    object SeatSelection : Screen("seat_selection/{flightId}") {
        fun createRoute(flightId: String) = "seat_selection/$flightId"
    }

    object Payment : Screen("payment/{flightId}/{seatId}") {
        fun createRoute(flightId: String, seatId: String) = "payment/$flightId/$seatId"
    }

    object BoardingPass : Screen("boarding_pass/{ticketId}") {
        fun createRoute(ticketId: String) = "boarding_pass/$ticketId"
    }

    object TicketList : Screen("ticket_list")
    object Profile : Screen("profile")
    object Notifications : Screen("notifications")
}
