package balitsky.newsapp.domain.usecase

import balitsky.newsapp.data.NewsRepository
import balitsky.newsapp.data.Result
import balitsky.newsapp.data.db.model.NewsDBO
import balitsky.newsapp.domain.model.NewsModel
import balitsky.newsapp.presentation.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(isForced: Boolean = false): Flow<UiState<List<NewsModel>>> {
        return newsRepository
            .fetchNews(isForced)
            .map { result ->
                when (result) {
                    is Result.Success -> UiState.Success(
                        data = result.data
                            .filter { it.urlToImage.isNotEmpty() }
                            .toNewsModel()
                    )
                    is Result.Error -> UiState.Error(result.message)
                    is Result.Loading -> UiState.Loading
                }
            }
    }

    private fun List<NewsDBO>.toNewsModel(): List<NewsModel> {
        return map { dbo ->

            var dayPublished = ""
            var monthPublished = ""

            try {
                with(LocalDate.parse(dbo.publishedAt.substring(0..9))) {
                    dayPublished = dayOfMonth.toString()
                    monthPublished = month.getDisplayName(
                        TextStyle.SHORT,
                        Locale.ENGLISH
                    )
                }
            } catch (_: Exception) {}

            NewsModel(
                id = dbo.id,
                title = dbo.title,
                description = dbo.description,
                date = "$dayPublished $monthPublished",
                imageUrl = dbo.urlToImage
            )
        }
    }
}
