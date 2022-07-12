package com.example.newsapppp.presentation.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapppp.R
import com.example.newsapppp.databinding.FragmentMainBinding
import com.example.newsapppp.presentation.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val newsAdapter by lazy { NewsAdapter() }
    private val viewModel by viewModels<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setHasOptionsMenu(true)

        viewModel.getNewsRetrofit()
        viewModel.myNews.observe(viewLifecycleOwner) {
            newsAdapter.setList(it)
        }

        newsAdapter.setOnItemClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewsFragment(it))
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_account) {
            findNavController().navigate(R.id.settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}
