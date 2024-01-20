package mazen.abdelgawad.news.domain.usecase

import mazen.abdelgawad.news.data.remote.models.NewsResponse
import mazen.abdelgawad.news.data.repo.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val detectDeviceLanguageUseCase: DetectDeviceLanguageUseCase
) {
    suspend operator fun invoke(searchKeyword: String): NewsResponse? {
        val languageCode = this.detectDeviceLanguageUseCase.invoke().languageCode
        return this.newsRepository.getNews(searchKeyword, "publishedAt", languageCode)
    }

}