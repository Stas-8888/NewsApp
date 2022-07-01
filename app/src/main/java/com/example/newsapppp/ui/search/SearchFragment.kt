package com.example.newsapppp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapppp.Application
import com.example.newsapppp.R
import com.example.newsapppp.SEARCH_NEWS_TIME_DELAY
import com.example.newsapppp.ui.adapters.NewsAdapter
import com.example.newsapppp.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val adapter by lazy { NewsAdapter() }

    private val viewModel: SearchFragmentViewModel by activityViewModels {
        SearchFragmentViewModel.MainViewModelFactory((context?.applicationContext as Application).database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_newsFragment,
                bundle
            )
        }

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.getSearchRetrofit(editable.toString())
                    }
                }
            }
        }
        viewModel.searchNews.observe(viewLifecycleOwner) { response ->
                        adapter.setList(response.body()!!.articles)
        }
    }

    private fun setupRecyclerView() {
        binding.rvSearchNews.adapter = adapter
        binding.rvSearchNews.layoutManager = LinearLayoutManager(requireContext())
    }
}