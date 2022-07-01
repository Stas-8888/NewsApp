package com.example.newsapppp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapppp.Application
import com.example.newsapppp.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    val args: NewsFragmentArgs by navArgs()

    private val viewModel: NewsFragmentViewModel by activityViewModels {
        NewsFragmentViewModel.MainViewModelFactory((context?.applicationContext as Application).database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url!!)
        }

        fab.setOnClickListener {
            viewModel.insert(article)
            Snackbar.make(view, "Статья добавлена в избранное", Snackbar.LENGTH_SHORT).show()
        }
    }
}