package mazen.abdelgawad.news.data.repo

import kotlinx.coroutines.flow.Flow
import mazen.abdelgawad.news.data.remote.models.Article
import mazen.abdelgawad.news.data.remote.models.NewsResponse

interface NewsRepo {
    suspend fun getNews(
        searchKeyword: String,
        sortBy: String,
        language: String
    ): NewsResponse? //Flow<List<Article>>
}