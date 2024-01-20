package mazen.abdelgawad.news.data.remote.securekey

import android.util.Base64
import mazen.abdelgawad.news.BuildConfig
import mazen.abdelgawad.news.common.security.CipherUtils
import mazen.abdelgawad.news.common.security.XORUtils


class ApiKeyUtils {

    fun getKey(secretKet: String) = buildKey(secretKet)

    private fun buildKey(secretKet: String): String? {
        return try {
            val data = getA(secretKet)
            val key = getD(secretKet)
            CipherUtils().decryptData(data, key)
        } catch (e: Throwable) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            null
        }
    }

    private fun getA(secretKet: String): String {
        val a = map(A().getA())
        return XORUtils().retrieveXorStrings(a, secretKet)
    }

    private fun getD(secretKet: String): String {
        val d = map(D().getD())
        return XORUtils().retrieveXorStrings(d, secretKet)
    }

    private fun map(data: String): String = String(Base64.decode(data, Base64.DEFAULT))
}