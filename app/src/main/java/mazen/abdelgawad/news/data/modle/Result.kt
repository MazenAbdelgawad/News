package mazen.abdelgawad.news.data.modle

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val failureReason: FailureReason) : Result<Nothing>()
}

sealed class FailureReason {
    data class UnknownError(val message: String?) : FailureReason()
    data object NoInternet : FailureReason()
    data object UnAuthorized : FailureReason()
    data class ServerError(val code: Int, val message: String?) : FailureReason()
    data class RemoteResponseParsingError(val message: String?) : FailureReason()
    data object ResourceNotFound : FailureReason()
}