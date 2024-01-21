package mazen.abdelgawad.news.presentation.news.newsdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import mazen.abdelgawad.news.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {
    private val binding by lazy { FragmentNewsDetailsBinding.inflate(layoutInflater) }
    private val args: NewsDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val news = args.selectedNews

        binding.zaas.text = news.title
        return binding.root
    }
}