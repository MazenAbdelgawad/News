package mazen.abdelgawad.news.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mazen.abdelgawad.news.data.modle.FailureReason
import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.di.base.IoDispatcher
import mazen.abdelgawad.news.domain.modle.News
import mazen.abdelgawad.news.domain.usecase.FetchAndroidNewsUseCase
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchAndroidNewsUseCase: FetchAndroidNewsUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val newsMutableState: MutableStateFlow<Result<List<News>?>> =
        MutableStateFlow(Result.Loading)

    fun fetchNews(): MutableStateFlow<Result<List<News>?>> {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                newsMutableState.value = Result.Loading
                newsMutableState.value = fetchAndroidNewsUseCase()
            } catch (e: Throwable) {
                Log.e("ViewModel", "error")
                e.printStackTrace()
                newsMutableState.value = Result.Failure(FailureReason.UnknownError(e.message))
            }
        }
        return newsMutableState
    }

}