package mazen.abdelgawad.news.data.remote


class UnknownErrorException : Exception()
class UnAuthorizedException : Exception()
class ServerErrorException(val code: Int) : Exception()
class NotFoundException : Exception()
class ResponseParsingException : Exception()
class NoNetworkConnectionException : Exception() {
    override val message: String
        get() = "No connection available, Please check your network"
}