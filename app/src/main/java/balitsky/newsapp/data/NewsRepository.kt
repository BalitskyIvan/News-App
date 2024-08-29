package balitsky.newsapp.data

import balitsky.newsapp.data.db.model.NewsDBO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun fetchNews(isForced: Boolean): Flow<Result<List<NewsDBO>>>

    suspend fun getNewsDetails(id: String): NewsDBO?
}
