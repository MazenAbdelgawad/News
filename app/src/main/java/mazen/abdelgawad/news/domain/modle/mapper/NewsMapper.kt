package mazen.abdelgawad.news.domain.modle.mapper

import mazen.abdelgawad.news.data.remote.models.Article
import mazen.abdelgawad.news.domain.modle.News

class NewsMapper {
    fun map(articles: List<Article>): List<News> {
        return articles.map {
            News(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = mapPublishedAt(it.publishedAt),
                source = it.source,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
        }
    }

    private fun mapPublishedAt(publishedAt: String?): String? =
        if (publishedAt.isNullOrBlank() || publishedAt.length < 10) publishedAt
        else publishedAt.substring(0, 10)

}