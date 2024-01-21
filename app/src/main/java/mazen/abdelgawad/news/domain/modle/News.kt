package mazen.abdelgawad.news.domain.modle

import mazen.abdelgawad.news.data.remote.models.Source

data class News(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)