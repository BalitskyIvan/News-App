package balitsky.newsapp.domain.model

data class NewsModel(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)
