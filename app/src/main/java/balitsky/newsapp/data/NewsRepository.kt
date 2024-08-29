package balitsky.newsapp.data

import balitsky.newsapp.data.model.NewsDBO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun fetchNews(isForced: Boolean): Flow<Result<List<NewsDBO>>>
}
