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

    fun handleNewsRecyclerView() {
        this.binding.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsListAdapter
        }
    }

    private fun onNewsItemClick(news: News) {
        show(news.title ?: "null title")
    }

    fun loadData() {
        lifecycleScope.launch {
            viewModel.fetchNews().collect { result ->
                when (result) {
                    is Result.Loading -> show("loading")
                    is Result.Failure -> show(result.failureReason.javaClass.simpleName)
                    is Result.Success -> {
                        show("total= ${result.data?.size}")
                        result.data?.let {
                            this@NewsListFragment.newsListAdapter.setItem(it)
                        }
                    }
                }
            }
        }
    }

    fun show(text: String) {
        Toast.makeText(this.activity, text, Toast.LENGTH_LONG).show()
        Log.d("ShowNews", text)
    }

}