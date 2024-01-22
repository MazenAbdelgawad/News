package mazen.abdelgawad.news.domain.usecase

import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.data.repo.NewsRepository
import mazen.abdelgawad.news.domain.modle.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val detectDeviceLanguageUseCase: DetectDeviceLanguageUseCase
) {
    suspend operator fun invoke(searchKeyword: String): Result<List<News>?> {
        val languageCode = this.detectDeviceLanguageUseCase.invoke().languageCode
        return this.newsRepository.getNews(searchKeyword, "publishedAt", languageCode)
    }

}