package mazen.abdelgawad.news.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mazen.abdelgawad.news.common.utils.NetworkState
import mazen.abdelgawad.news.common.utils.NetworkStateMonitor
import mazen.abdelgawad.news.data.modle.FailureReason
import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.di.base.IoDispatcher
import mazen.abdelgawad.news.domain.modle.News
import mazen.abdelgawad.news.domain.usecase.FetchAndroidNewsUseCase
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchAndroidNewsUseCase: FetchAndroidNewsUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val networkStateMonitor: NetworkStateMonitor,
) :
    ViewModel() {

    private val newsMutableState: MutableStateFlow<Result<List<News>?>> =
        MutableStateFlow(Result.Loading)

    fun fetchNews(): StateFlow<Result<List<News>?>> {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                newsMutableState.value = Result.Loading
                newsMutableState.value = fetchAndroidNewsUseCase()
            } catch (e: Throwable) {
                e.printStackTrace()
                newsMutableState.value = Result.Failure(FailureReason.UnknownError(e.message))
            }
        }
        return newsMutableState
    }

    fun getCurrentNetworkState() = networkStateMonitor.getNetworkState()

    fun fetchNetworkState(): StateFlow<NetworkState> = networkStateMonitor.observeNetworkState()

}