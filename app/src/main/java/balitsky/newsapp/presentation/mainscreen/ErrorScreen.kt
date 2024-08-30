package balitsky.newsapp.presentation.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eu.bambooapps.material3.pullrefresh.PullRefreshState
import eu.bambooapps.material3.pullrefresh.pullRefresh

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(
    errorText: String,
    pullRefreshState: PullRefreshState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Error happened :(",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(top = 40.dp),
            text = errorText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
