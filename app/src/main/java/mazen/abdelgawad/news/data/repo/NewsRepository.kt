package mazen.abdelgawad.news.data.repo

import mazen.abdelgawad.news.data.modle.FailureReason
import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.data.remote.NewsRemoteDataSource
import mazen.abdelgawad.news.data.remote.NoNetworkConnectionException
import mazen.abdelgawad.news.data.remote.NotFoundException
import mazen.abdelgawad.news.data.remote.ResponseParsingException
import mazen.abdelgawad.news.data.remote.ServerErrorException
import mazen.abdelgawad.news.data.remote.UnAuthorizedException
import mazen.abdelgawad.news.data.remote.UnknownErrorException
import mazen.abdelgawad.news.domain.modle.News
import mazen.abdelgawad.news.domain.modle.mapper.NewsMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRepo {
    override suspend fun getNews(
        searchKeyword: String,
        sortBy: String,
        language: String
    ): Result<List<News>?> {
        return this.getNewsFromApi(searchKeyword, sortBy, language)
    }

    private suspend fun getNewsFromApi(
        searchKeyword: String,
        sortBy: String,
        language: String
    ): Result<List<News>?> {
        try {
            val response = this.newsRemoteDataSource.getNews(searchKeyword, sortBy, language)
            return if (response?.articles == null || response.articles.isEmpty()) {
                Result.Failure(FailureReason.ResourceNotFound)
            } else {
                Result.Success(NewsMapper().map(response.articles))
            }
        } catch (e: Throwable) {
            return when (e) {
                is UnAuthorizedException -> Result.Failure(FailureReason.UnAuthorized)
                is ServerErrorException -> Result.Failure(
                    FailureReason.ServerError(
                        e.code,
                        e.message
                    )
                )

                is NotFoundException -> Result.Failure(FailureReason.ResourceNotFound)
                is ResponseParsingException -> Result.Failure(
                    FailureReason.RemoteResponseParsingError(
                        e.message
                    )
                )

                is NoNetworkConnectionException -> Result.Failure(FailureReason.NoInternet)
                is UnknownErrorException -> Result.Failure(FailureReason.UnknownError(e.message))
                else -> Result.Failure(FailureReason.UnknownError(e.message))
            }
        }
    }

}