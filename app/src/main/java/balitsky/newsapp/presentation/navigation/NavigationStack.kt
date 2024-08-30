package balitsky.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import balitsky.newsapp.presentation.detailsscreen.DetailsScreen
import balitsky.newsapp.presentation.mainscreen.MainScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.NewsDetail.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            DetailsScreen(
                navController = navController,
                id = it.arguments?.getString("id") ?: ""
            )
        }
    }
}
