package com.example.flightapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.a5.flightbooking.navigation.Screen
import com.example.flightapp.screens.boardingpass.BoardingPassScreen
import com.example.flightapp.screens.home.HomeScreen
import com.example.flightapp.screens.notifications.NotificationsScreen
import com.example.flightapp.screens.onboarding.OnboardingScreen
import com.example.flightapp.screens.payment.PaymentScreen
import com.example.flightapp.screens.profile.ProfileScreen
import com.example.flightapp.screens.results.SearchResultsScreen
import com.example.flightapp.screens.seatselection.SeatSelectionScreen
import com.example.flightapp.screens.tickets.MyTicketsScreen

// This file is the one everyone shares - only whoever owns Phase 0 should be
// editing it after setup. If a screen needs a new nav argument, add it here
// and ping the group rather than everyone hand-editing this on their own branch.
@Composable
fun FlightAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onGetStarted = { navController.navigate(Screen.Home.route) }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onSearch = { navController.navigate(Screen.SearchResults.route) },
                onOpenTicket = { flightId ->
                    navController.navigate(Screen.SeatSelection.createRoute(flightId))
                },
                onNotificationClick = { navController.navigate(Screen.Notifications.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onTicketClick = { navController.navigate(Screen.TicketList.route) }
            )
        }

        composable(Screen.SearchResults.route) {
            SearchResultsScreen(
                onSelectFlight = { flightId ->
                    navController.navigate(Screen.SeatSelection.createRoute(flightId))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.SeatSelection.route,
            arguments = listOf(navArgument("flightId") { type = NavType.StringType })
        ) { backStackEntry ->
            val flightId = backStackEntry.arguments?.getString("flightId") ?: ""
            SeatSelectionScreen(
                flightId = flightId,
                onCheckout = { seatId -> navController.navigate(Screen.Payment.createRoute(flightId, seatId)) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Payment.route,
            arguments = listOf(
                navArgument("flightId") { type = NavType.StringType },
                navArgument("seatId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val flightId = backStackEntry.arguments?.getString("flightId") ?: ""
            val seatId = backStackEntry.arguments?.getString("seatId") ?: ""
            PaymentScreen(
                flightId = flightId,
                seatId = seatId,
                onPaySuccess = { ticketId ->
                    navController.navigate(Screen.BoardingPass.createRoute(ticketId)) {
                        // clear the booking flow off the back stack so "back" from the
                        // boarding pass goes to Home, not back through payment/seats
                        popUpTo(Screen.Home.route)
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.BoardingPass.route,
            arguments = listOf(navArgument("ticketId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId") ?: ""
            BoardingPassScreen(
                ticketId = ticketId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.TicketList.route) {
            MyTicketsScreen(
                onBack = { navController.popBackStack() },
                onHomeClick = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } } },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onNotificationClick = { navController.navigate(Screen.Notifications.route) },
                onTicketClick = { flightId -> navController.navigate(Screen.SeatSelection.createRoute(flightId)) }
            )
        }
    }
}
