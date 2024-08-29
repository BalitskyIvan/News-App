package balitsky.newsapp.data.api

import balitsky.newsapp.BuildConfig
import balitsky.newsapp.data.model.NewsDTO
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("/top-headlines?q=tech&apiKey=${BuildConfig.API_KEY}")
    suspend fun fetchNews(): Response<NewsDTO>

}