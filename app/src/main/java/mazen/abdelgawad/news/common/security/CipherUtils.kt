package mazen.abdelgawad.news.common.security

import android.util.Base64
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class CipherUtils {
    fun decryptData(encryptedData: String, publicKeyBase64: String): String {
        val publicKeyBytes = Base64.decode(publicKeyBase64, Base64.DEFAULT)
        val publicKeySpec = X509EncodedKeySpec(publicKeyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKey: PublicKey = keyFactory.generatePublic(publicKeySpec)

        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, publicKey)

        val encryptedBytes = Base64.decode(encryptedData, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        return String(decryptedBytes)
    }

    fun encryptData(data: String, privateKeyBase64: String): String {
        val privateKeyBytes = Base64.decode(privateKeyBase64, Base64.DEFAULT)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey: PrivateKey = keyFactory.generatePrivate(privateKeySpec)

        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, privateKey)

        val encryptedBytes = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    fun generateKeyPair(): Pair<String, String> {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)

        val keyPair = keyPairGenerator.generateKeyPair()

        val privateKeyBase64 = Base64.encodeToString(keyPair.private.encoded, Base64.DEFAULT)
        val publicKeyBase64 = Base64.encodeToString(keyPair.public.encoded, Base64.DEFAULT)

        return Pair(privateKeyBase64, publicKeyBase64)
    }
}