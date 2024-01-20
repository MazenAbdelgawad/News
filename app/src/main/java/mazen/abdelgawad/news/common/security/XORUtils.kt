package mazen.abdelgawad.news.common.security

class XORUtils {

    fun xorStrings(text: String, xorKey: String): String {
        val length = maxOf(text.length, xorKey.length)
        val repeatedText = repeatToLength(text, length)
        val repeatedXorKey = repeatToLength(xorKey, length)

        val result = StringBuilder()
        for (i in repeatedText.indices) {
            result.append((repeatedText[i].toInt() xor repeatedXorKey[i].toInt()).toChar())
        }
        return result.toString()
    }

    fun retrieveXorStrings(xorResult: String, xorKey: String): String {
        val repeatedXorKey = repeatToLength(xorKey, xorResult.length)

        val originalText = StringBuilder()
        for (i in xorResult.indices) {
            originalText.append((xorResult[i].toInt() xor repeatedXorKey[i].toInt()).toChar())
        }
        return originalText.toString()
    }

    private fun repeatToLength(str: String, length: Int): String {
        val repeated = StringBuilder()
        var i = 0
        while (repeated.length < length) {
            repeated.append(str[i])
            i = (i + 1) % str.length
        }
        return repeated.toString()
    }
}