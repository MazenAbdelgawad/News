package mazen.abdelgawad.news.data.remote

import mazen.abdelgawad.news.data.remote.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("q") searchKeyword: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String
    ): NewsResponse
}