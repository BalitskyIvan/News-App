package balitsky.newsapp.data.model

data class NewsDTO(
    val articles: List<ArticlesDTO>
)

data class ArticlesDTO(
    val title: String,
    val description: String,
    val content: String,
    val urlToImage: String,
    val publishedAt: String
)
