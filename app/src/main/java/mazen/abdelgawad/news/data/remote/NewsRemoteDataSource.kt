package mazen.abdelgawad.news.data.remote

import mazen.abdelgawad.news.data.remote.executor.RemoteExecutor
import mazen.abdelgawad.news.data.remote.models.NewsResponse
import retrofit2.http.Query
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val newsApi: NewsApi) : RemoteExecutor() {
    suspend fun getNews(
        @Query("q") searchKeyword: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String
    ): NewsResponse? {
        return executeApiCall { newsApi.getNews(searchKeyword, sortBy, language) }
    }
}