package mazen.abdelgawad.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.presentation.news.NewsViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.fetchNews().collect { result ->
                when (result) {
                    is Result.Loading -> show("loading")
                    is Result.Failure -> show(result.failureReason.javaClass.simpleName)
                    is Result.Success -> show("total= ${result.data?.size}")
                }
            }
        }
    }

    fun show(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        Log.d("ShowNews", text)
    }
}