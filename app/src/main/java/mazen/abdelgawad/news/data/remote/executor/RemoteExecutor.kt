package mazen.abdelgawad.news.data.remote.executor

import com.squareup.moshi.JsonDataException
import mazen.abdelgawad.news.data.remote.*
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class RemoteExecutor {
    @Throws(
        UnknownErrorException::class,
        UnAuthorizedException::class,
        ServerErrorException::class,
        ResponseParsingException::class,
        NotFoundException::class,
        NoNetworkConnectionException::class
    )

    protected suspend fun <T> executeApiCall(apiCall: suspend () -> T): T? {
        return try {
            apiCall.invoke()
        } catch (cause: Throwable) {
            mapApiCallException(cause)
            null
        }
    }

    private fun mapApiCallException(error: Throwable) {
        when (error) {
            is SocketTimeoutException -> throw NoNetworkConnectionException()
            is UnknownHostException -> throw NoNetworkConnectionException()
            is JsonDataException -> throw ResponseParsingException()
            is HttpException -> mapErrorResponse(error.response())
            else -> throw error
        }
    }

    private fun mapErrorResponse(response: Response<*>?) {
        when {
            response?.code() == 401 -> throw UnAuthorizedException()
            response?.code() == 404 -> throw NotFoundException()
            response?.code() == 500 -> throw ServerErrorException(response.code())
            else -> throw UnknownErrorException()
        }
    }
}