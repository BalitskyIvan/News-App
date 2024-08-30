package balitsky.newsapp.presentation.navigation

sealed class Screen(val route: String) {
    data object Main: Screen("main_screen")
    data object NewsDetail: Screen("news_detail_screen")
}
