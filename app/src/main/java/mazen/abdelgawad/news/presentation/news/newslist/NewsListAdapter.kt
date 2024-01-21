package mazen.abdelgawad.news.presentation.news.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mazen.abdelgawad.news.R
import mazen.abdelgawad.news.databinding.CardNewsBinding
import mazen.abdelgawad.news.domain.modle.News

class NewsListAdapter(
    private var newsList: List<News>,
    private val onItemClick: (news: News) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private lateinit var context: Context

    inner class NewsViewHolder(val binding: CardNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            CardNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]

        with(holder.binding) {
            title.text = item.title ?: item.description ?: ""
            date.text = item.publishedAt ?: ""

            if (item.urlToImage.isNullOrBlank()) {
                image.visibility = View.GONE
            } else {
                image.visibility = View.VISIBLE
                Glide
                    .with(context)
                    .load(item.urlToImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_img)
                    .into(image)
            }

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.context = recyclerView.context
    }

    fun setItem(newsList: List<News>) {
        this.newsList = newsList
        super.notifyItemRangeChanged(0, newsList.size)
    }
}