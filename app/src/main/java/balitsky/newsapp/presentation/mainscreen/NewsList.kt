package balitsky.newsapp.presentation.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balitsky.newsapp.domain.model.NewsModel
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NewsList(
    modifier: Modifier,
    newsList: StateFlow<List<NewsModel>>,
    onNewsClicked: (id: String) -> Unit
) {
    val news = newsList.collectAsState().value

    LazyColumn(
        modifier = modifier
            .padding(top = 40.dp)
            .padding(horizontal = 10.dp),
    ) {
        items(
            items = news,
            key = { it.id }
        ) { newsItem ->

            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(12.dp))
                    .clickable {
                        onNewsClicked.invoke(newsItem.id)
                    }
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    model = newsItem.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "News Image"
                )

                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(horizontal = 16.dp),
                    text = newsItem.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .padding(horizontal = 16.dp),
                    text = newsItem.description,
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 5.dp)
                        .padding(horizontal = 16.dp),
                    text = newsItem.date,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
