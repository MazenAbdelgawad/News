package mazen.abdelgawad.news.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mazen.abdelgawad.news.common.utils.NetworkStateMonitor
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Singleton
    @Provides
    fun provideLocale(): Locale = Locale.getDefault()

    @Singleton
    @Provides
    fun provideNetworkStateMonitor(@ApplicationContext applicationContext: Context): NetworkStateMonitor {
        return NetworkStateMonitor(applicationContext)
    }
}