package com.a5.flightbooking.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object MyTickets : Screen("my_tickets")
    object BoardingPass : Screen("boarding_pass")
    object SelectSeat : Screen("select_seat")
    object Payment : Screen("payment")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")
    object History : Screen("history")
}
