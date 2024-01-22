package mazen.abdelgawad.news.domain.usecase

import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.domain.modle.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchAndroidNewsUseCase @Inject constructor(private val fetchNewsUseCase: FetchNewsUseCase) {
    suspend operator fun invoke(): Result<List<News>?> {
        return this.fetchNewsUseCase("Android")
    }

}