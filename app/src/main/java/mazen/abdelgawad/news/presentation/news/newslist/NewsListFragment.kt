package mazen.abdelgawad.news.presentation.news.newslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mazen.abdelgawad.news.R
import mazen.abdelgawad.news.data.modle.FailureReason
import mazen.abdelgawad.news.data.modle.Result
import mazen.abdelgawad.news.databinding.FragmentNewsListBinding
import mazen.abdelgawad.news.domain.modle.News
import mazen.abdelgawad.news.presentation.news.NewsViewModel

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    private val binding by lazy { FragmentNewsListBinding.inflate(layoutInflater) }
    private val newsListAdapter by lazy { NewsListAdapter(emptyList(), this::onNewsItemClick) }
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.loadData()
        this.handleNewsRecyclerView()

        return binding.root
    }

    private fun handleNewsRecyclerView() {
        this.binding.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsListAdapter
        }
    }

    private fun onNewsItemClick(news: News) {
        show(news.title ?: "null title")
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.fetchNews().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        removeError()
                        startShimmer()
                    }

                    is Result.Failure -> {
                        stopShimmer()
                        handleFailureState(result.failureReason)
                    }

                    is Result.Success -> {
                        removeError()
                        stopShimmer()
                        handleSuccessState(result.data)
                    }
                }
            }
        }
    }

    private fun handleSuccessState(news: List<News>?) {
        if (news.isNullOrEmpty()) {
            this.showError(R.string.no_data_found)
        } else {
            this.newsListAdapter.setItem(news)
        }
    }

    private fun handleFailureState(failureReason: FailureReason) {
        when (failureReason) {
            is FailureReason.NoInternet -> showError(
                R.string.no_internet_check_your_connection,
                R.drawable.img_no_internet
            )

            is FailureReason.RemoteResponseParsingError -> showError(
                getString(R.string.remote_response_parsing_error) + " [${failureReason.message}]"
            )

            is FailureReason.ServerError -> showError(
                getString(R.string.remote_response_parsing_error) +
                        " [${failureReason.code} , ${failureReason.message}]"
            )

            is FailureReason.UnknownError -> showError(
                getString(R.string.remote_response_parsing_error) + " [${failureReason.message}]"
            )

            is FailureReason.ResourceNotFound -> showError(R.string.resource_not_found)
            is FailureReason.UnAuthorized -> showError(R.string.unauthorized)
        }
    }

    private fun showError(msgResourceId: Int, imgResourceId: Int = R.drawable.img_error) {
        this.showError(getString(msgResourceId), imgResourceId)
    }

    private fun showError(msg: String, imgResourceId: Int = R.drawable.img_error) {
        with(binding) {
            errorLayout.visibility = View.VISIBLE
            errorText.text = msg
            errorImage.setImageResource(imgResourceId)
        }
    }

    private fun removeError() {
        with(binding) {
            errorLayout.visibility = View.GONE
            errorText.text = ""
            errorImage.setImageResource(R.drawable.img_error)
        }
    }

    private fun show(text: String) {
        Toast.makeText(this.activity, text, Toast.LENGTH_LONG).show()
        Log.d("ShowNews", text)
    }

    private fun startShimmer() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recyclerViewNews.visibility = View.GONE
    }

    private fun stopShimmer() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recyclerViewNews.visibility = View.VISIBLE
    }

}