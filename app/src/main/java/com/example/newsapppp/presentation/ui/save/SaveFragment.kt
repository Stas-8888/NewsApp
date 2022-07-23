package com.example.newsapppp.presentation.ui.save

import android.os.Bundle
import android.view.*
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapppp.R
import com.example.newsapppp.databinding.FragmentSaveBinding
import com.example.newsapppp.presentation.ui.adapters.NewsAdapter
import com.example.newsapppp.presentation.utils.extensions.showDeleteDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_save.*

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding
    private val newsAdapter by lazy { NewsAdapter() }
    private val viewModel by viewModels<SaveFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSaveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setHasOptionsMenu(true)
        btDeleteAll.setOnClickListener{
            deleteAllNotes()
        }

        if (rvSavedNews.isEmpty()) {
            tvBackgroundText.visibility = View.VISIBLE

        } else {
            tvBackgroundText.visibility = View.GONE
        }

        newsAdapter.setOnItemClickListener {
            findNavController().navigate(SaveFragmentDirections.actionSaveFragmentToNewsFragment(it))
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder) = 0.3f

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.listNews[position]
                showDeleteDialog({ viewModel.delete(article) }, { newsAdapter.notifyDataSetChanged() })
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                newsAdapter.setList(it)
                viewModel.getAllNews()
            }
        }
    }
//        viewModel.state.observe(viewLifecycleOwner) {
//            when (it) {
//                is SaveState.SetArticles -> {
//                    newsAdapter.setList(it.articles)
//                }
//            }
//        }
//        viewModel.getAllNews()
//    }

    private fun setupRecyclerView() = with(binding) {
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun deleteAllNotes() {
        showDeleteDialog({ viewModel.deleteAll() }, { })
    }
}
