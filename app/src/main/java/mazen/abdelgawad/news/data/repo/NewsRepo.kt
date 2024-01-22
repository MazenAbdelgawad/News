package mazen.abdelgawad.news.data.repo

import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.domain.modle.News

interface NewsRepo {
    suspend fun getNews(
        searchKeyword: String,
        sortBy: String,
        language: String
    ): Result<List<News>?>
}