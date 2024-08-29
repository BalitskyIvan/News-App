package balitsky.newsapp.data.di

import android.content.Context
import androidx.room.Room
import balitsky.newsapp.data.api.NewsApi
import balitsky.newsapp.data.NewsRepository
import balitsky.newsapp.data.NewsRepositoryImpl
import balitsky.newsapp.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext
        context: Context
    ): NewsDatabase = Room.databaseBuilder(context, NewsDatabase::class.java, "news-database").build()

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDatabase: NewsDatabase,
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(
        newsApi = newsApi,
        newsDatabase = newsDatabase
    )

}
