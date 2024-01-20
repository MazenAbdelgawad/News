package mazen.abdelgawad.news.data.repo

import kotlinx.coroutines.flow.Flow
import mazen.abdelgawad.news.data.remote.NewsRemoteDataSource
import mazen.abdelgawad.news.data.remote.models.Article
import mazen.abdelgawad.news.data.remote.models.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRepo {
    override suspend fun getNews(
        searchKeyword: String,
        sortBy: String,
        language: String
    ): NewsResponse? {//Flow<List<Article>> {
        return this.newsRemoteDataSource.getNews(searchKeyword, sortBy, language)
    }
}