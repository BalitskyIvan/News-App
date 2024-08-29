package balitsky.newsapp.domain.usecase

import balitsky.newsapp.data.NewsRepository
import balitsky.newsapp.domain.model.NewsDetailModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsDetailUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(id: String): NewsDetailModel? {
        val newsDetailsDBO = newsRepository
            .getNewsDetails(id = id)

        return if (newsDetailsDBO == null) {
            null
        } else {
            NewsDetailModel(
                title = newsDetailsDBO.title,
                content = newsDetailsDBO.content,
                imageUrl = newsDetailsDBO.urlToImage
            )
        }
    }
}
