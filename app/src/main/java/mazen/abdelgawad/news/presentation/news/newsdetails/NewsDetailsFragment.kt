package mazen.abdelgawad.news.presentation.news.newsdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mazen.abdelgawad.news.R
import mazen.abdelgawad.news.databinding.FragmentNewsDetailsBinding
import mazen.abdelgawad.news.domain.modle.News

class NewsDetailsFragment : Fragment() {
    private val binding by lazy { FragmentNewsDetailsBinding.inflate(layoutInflater) }
    private val args: NewsDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.shwNewsDetails(args.selectedNews)

        return binding.root
    }

    fun shwNewsDetails(item: News) = with(binding) {
        title.text = item.title ?: ""
        author.text = item.author ?: ""
        publishedAt.text = item.publishedAt ?: ""
        description.text = item.description
        url.text = item.url ?: "https://www.google.com/search?q=$title"

        if (item.urlToImage.isNullOrBlank()) {
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
            Glide
                .with(this@NewsDetailsFragment)
                .load(item.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.placeholder_img)
                .into(imageView)
        }
    }

}