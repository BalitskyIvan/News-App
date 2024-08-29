package balitsky.newsapp.presentation.detailsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    id: String,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    viewModel.initWithId(id = id)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = viewModel.urlImage.collectAsState().value,
            contentScale = ContentScale.Crop,
            contentDescription = "News Image"
        )

        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 16.dp),
            text = viewModel.title.collectAsState().value,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            modifier = Modifier
                .padding(top = 14.dp)
                .padding(horizontal = 16.dp),
            text = viewModel.content.collectAsState().value,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
