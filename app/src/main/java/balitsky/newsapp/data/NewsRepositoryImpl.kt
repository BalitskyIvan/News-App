package balitsky.newsapp.data

import balitsky.newsapp.data.api.NewsApi
import balitsky.newsapp.data.db.NewsDatabase
import balitsky.newsapp.data.db.model.NewsDBO
import balitsky.newsapp.data.api.model.NewsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : NewsRepository {

    private val news = MutableStateFlow<List<NewsDBO>>(listOf())

    override suspend fun fetchNews(isForced: Boolean): Flow<Result<List<NewsDBO>>> {
        return flow {
            emit(Result.Loading)
            if (!isForced) {
                emit(Result.Success(newsDatabase.newsDao().getAll()))
            }
            try {
                emit(getNewsFromApi())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getNewsDetails(id: String): NewsDBO? {
        return news.value.find { it.id == id }
    }

    private suspend fun getNewsFromApi(): Result<List<NewsDBO>> {
        val fetchedNewsResult = newsApi
            .fetchNews()
        return if (fetchedNewsResult.isSuccessful) {
            val news = fetchedNewsResult.body()?.toDBO()

            news?.let {
                newsDatabase.newsDao().saveAll(news)
            }
            Result.Success(news ?: emptyList())
        } else {
            Result.Error(fetchedNewsResult.message())
        }
    }

    private fun NewsDTO.toDBO(): List<NewsDBO> {
        return articles.map { article ->
            NewsDBO(
                id = UUID.randomUUID().toString(),
                title = article.title,
                urlToImage = article.urlToImage ?: "",
                description = article.description,
                content = article.content ?: "",
                publishedAt = article.publishedAt
            )
        }
    }

}
