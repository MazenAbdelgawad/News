package mazen.abdelgawad.news.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mazen.abdelgawad.news.data.remote.NewsRemoteDataSource
import mazen.abdelgawad.news.data.repo.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository {
        return NewsRepository(newsRemoteDataSource)
    }
}