package balitsky.newsapp.presentation.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    id: String,
    navController: NavController,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    viewModel.initWithId(id = id)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
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
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 50.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = "Back Arrow"
            )
        }
    }
}
