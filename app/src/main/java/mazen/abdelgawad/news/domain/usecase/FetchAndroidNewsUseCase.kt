package mazen.abdelgawad.news.domain.usecase

import mazen.abdelgawad.news.data.remote.models.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchAndroidNewsUseCase @Inject constructor(private val fetchNewsUseCase: FetchNewsUseCase) {
    suspend operator fun invoke(): NewsResponse? {
        return this.fetchNewsUseCase("Android")
    }

}