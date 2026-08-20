package com.flightapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flightapp.ui.screens.*

@Composable
fun FlightNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onGetStarted = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onTicketClick = { navController.navigate(Screen.MyTickets.route) },
                onNotificationClick = { navController.navigate(Screen.Notifications.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }
        composable(Screen.MyTickets.route) {
            MyTicketsScreen(
                onBack = { navController.popBackStack() },
                onTicketClick = { navController.navigate(Screen.BoardingPass.route) },
                onNotificationClick = { navController.navigate(Screen.Notifications.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onHomeClick = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }}
            )
        }
        composable(Screen.BoardingPass.route) {
            BoardingPassScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.SelectSeat.route) {
            SelectSeatScreen(
                onBack = { navController.popBackStack() },
                onCheckout = { navController.navigate(Screen.Payment.route) }
            )
        }
        composable(Screen.Payment.route) {
            PaymentScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Profile.route) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
    }
}
