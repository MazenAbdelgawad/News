package mazen.abdelgawad.news.domain.usecase

import mazen.abdelgawad.news.domain.modle.DeviceLanguage
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetectDeviceLanguageUseCase @Inject constructor(private val locale: Locale) {
    operator fun invoke(): DeviceLanguage =
        if (locale.language == DeviceLanguage.ARABIC.languageCode) DeviceLanguage.ARABIC
        else DeviceLanguage.ENGLISH

}