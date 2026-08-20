package com.example.flightapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.a5.flightbooking.navigation.Screen1
import com.a5.flightbooking.ui.screens.boardingpass.BoardingPassScreen
import com.a5.flightbooking.ui.screens.home.HomeScreen
import com.a5.flightbooking.ui.screens.onboarding.OnboardingScreen
import com.a5.flightbooking.ui.screens.payment.PaymentScreen
import com.a5.flightbooking.ui.screens.profile.ProfileScreen
import com.a5.flightbooking.ui.screens.results.SearchResultsScreen
import com.a5.flightbooking.ui.screens.seatselection.SeatSelectionScreen

// This file is the one everyone shares - only whoever owns Phase 0 should be
// editing it after setup. If a screen needs a new nav argument, add it here
// and ping the group rather than everyone hand-editing this on their own branch.
@Composable
fun FlightAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen1.Onboarding.route
    ) {
        composable(Screen1.Onboarding.route) {
            OnboardingScreen(
                onGetStarted = { navController.navigate(Screen1.Home.route) }
            )
        }

        composable(Screen1.Home.route) {
            HomeScreen(
                onSearch = { navController.navigate(Screen1.SearchResults.route) },
                onOpenTicket = { flightId ->
                    navController.navigate(Screen1.SeatSelection.createRoute(flightId))
                }
            )
        }

        composable(Screen1.SearchResults.route) {
            SearchResultsScreen(
                onSelectFlight = { flightId ->
                    navController.navigate(Screen1.SeatSelection.createRoute(flightId))
                }
            )
        }

        composable(
            route = Screen1.SeatSelection.route,
            arguments = listOf(navArgument("flightId") { type = NavType.StringType })
        ) { backStackEntry ->
            val flightId = backStackEntry.arguments?.getString("flightId") ?: ""
            SeatSelectionScreen(
                flightId = flightId,
                onCheckout = { navController.navigate(Screen1.Payment.createRoute(flightId)) }
            )
        }

        composable(
            route = Screen1.Payment.route,
            arguments = listOf(navArgument("flightId") { type = NavType.StringType })
        ) { backStackEntry ->
            val flightId = backStackEntry.arguments?.getString("flightId") ?: ""
            PaymentScreen(
                flightId = flightId,
                onPaySuccess = { ticketId ->
                    navController.navigate(Screen1.BoardingPass.createRoute(ticketId)) {
                        // clear the booking flow off the back stack so "back" from the
                        // boarding pass goes to Home, not back through payment/seats
                        popUpTo(Screen1.Home.route)
                    }
                }
            )
        }

        composable(
            route = Screen1.BoardingPass.route,
            arguments = listOf(navArgument("ticketId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId") ?: ""
            BoardingPassScreen(ticketId = ticketId)
        }

        composable(Screen1.Profile.route) {
            ProfileScreen()
        }
    }
}
