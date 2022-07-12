package com.example.newsapppp.presentation.ui.news

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapppp.R
import com.example.newsapppp.databinding.FragmentNewsBinding
import com.example.newsapppp.presentation.utils.SaveShared
import com.example.newsapppp.presentation.utils.extensions.showAlertAdd
import com.example.newsapppp.presentation.utils.extensions.showAlertDelete
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val args: NewsFragmentArgs by navArgs()
    private val viewModel by viewModels<NewsFragmentViewModel>()

    var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initi()

    }

//    private fun saveState(){
//        val sharedPreferences = this.activity?.getSharedPreferences("sss", Context.MODE_PRIVATE)
//        val editor = sharedPreferences?.edit()
//        editor.apply{
//            put("sss", binding.btFavorite.setImageResource(R.drawable.ic_favorite))
//        }
//    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initi() {
        val article = args.article
        val valueBool = SaveShared.getFavorite(requireContext(), article.id.toString())

        webView.apply {
            webView.webViewClient = WebViewClient()
            loadUrl(article.url!!)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }


        if (isFavorite != valueBool) {
            binding.btFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.btFavorite.setImageResource(R.drawable.ic_favorite_border)
        }

        binding.btFavorite.setOnClickListener {
            if (isFavorite == valueBool) {
                binding.btFavorite.setImageResource(R.drawable.ic_favorite)
                SaveShared.setFavorite(requireContext(), article.id.toString(), true)
                viewModel.insert(article)
                showAlertAdd()
//                Snackbar.make(view, "Статья добавлена в избранное", Snackbar.LENGTH_SHORT).show()
                isFavorite = true
            } else {
                SaveShared.setFavorite(requireContext(), article.id.toString(), false)
                binding.btFavorite.setImageResource(R.drawable.ic_favorite_border)
                viewModel.delete(article)
                showAlertDelete()
//                Snackbar.make(view, "Статья удалена из избранного", Snackbar.LENGTH_SHORT).show()
                isFavorite = false
            }
        }
    }
}
