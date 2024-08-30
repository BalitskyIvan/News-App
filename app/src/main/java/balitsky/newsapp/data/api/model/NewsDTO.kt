package balitsky.newsapp.data.api.model

data class NewsDTO(
    val articles: List<ArticlesDTO>
)

data class ArticlesDTO(
    val title: String,
    val description: String? = null,
    val content: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String
)
