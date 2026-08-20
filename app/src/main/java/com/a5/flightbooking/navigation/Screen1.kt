package com.a5.flightbooking.navigation

sealed class Screen1(val route: String) {
    object Onboarding : Screen1("onboarding")
    object Home : Screen1("home")
    object SearchResults : Screen1("search_results")

    object SeatSelection : Screen1("seat_selection/{flightId}") {
        fun createRoute(flightId: String) = "seat_selection/$flightId"
    }

    object Payment : Screen1("payment/{flightId}") {
        fun createRoute(flightId: String) = "payment/$flightId"
    }

    object BoardingPass : Screen1("boarding_pass/{ticketId}") {
        fun createRoute(ticketId: String) = "boarding_pass/$ticketId"
    }

    object TicketList : Screen1("ticket_list")
    object Profile : Screen1("profile")
    object Notifications : Screen1("notifications")
}
