package mazen.abdelgawad.news.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mazen.abdelgawad.news.data.repo.NewsRepository
import mazen.abdelgawad.news.domain.usecase.DetectDeviceLanguageUseCase
import mazen.abdelgawad.news.domain.usecase.FetchAndroidNewsUseCase
import mazen.abdelgawad.news.domain.usecase.FetchNewsUseCase
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideDetectDeviceLanguageUseCase(locale: Locale): DetectDeviceLanguageUseCase {
        return DetectDeviceLanguageUseCase(locale)
    }

    @Singleton
    @Provides
    fun provideFetchNewsUseCase(
        newsRepository: NewsRepository,
        deviceLanguageUseCase: DetectDeviceLanguageUseCase
    ): FetchNewsUseCase {
        return FetchNewsUseCase(newsRepository, deviceLanguageUseCase)
    }

    @Singleton
    @Provides
    fun provideFetchAndroidNewsUseCase(fetchNewsUseCase: FetchNewsUseCase): FetchAndroidNewsUseCase {
        return FetchAndroidNewsUseCase(fetchNewsUseCase)
    }
}