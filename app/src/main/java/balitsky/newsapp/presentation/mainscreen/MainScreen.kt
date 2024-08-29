package balitsky.newsapp.presentation.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import balitsky.newsapp.presentation.navigation.Screen
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val isLoadingVisible = viewModel.isLoadingVisible.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoadingVisible.value,
        onRefresh = viewModel::fetchNewList
    )

    Box {
        if (isLoadingVisible.value) {
            NewsListPlaceholder()
        } else {
            val error = viewModel.error.collectAsState()

            if (error.value.isEmpty()) {
                NewsList(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState),
                    onNewsClicked = { id ->
                        navController.navigate(route = Screen.NewsDetail.route + "?id=${id}")
                    },
                    newsList = viewModel.newsList
                )
            } else {
                ErrorScreen(
                    errorText = error.value,
                    pullRefreshState = pullRefreshState
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isLoadingVisible.value,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
    }
}
