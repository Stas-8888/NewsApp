package com.example.newsapppp.ui.save

import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapppp.Application
import com.example.newsapppp.R
import com.example.newsapppp.ui.adapters.NewsAdapter
import com.example.newsapppp.databinding.FragmentSaveBinding
import com.example.newsapppp.dialog.DeleteDialog
import kotlinx.android.synthetic.main.fragment_save.*

class SaveFragment : Fragment() {
    lateinit var binding: FragmentSaveBinding
    val adapter by lazy { NewsAdapter() }

    private val viewModel: SaveFragmentViewModel by activityViewModels {
        SaveFragmentViewModel.MainViewModelFactory((context?.applicationContext as Application).daoNews)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_saveFragment_to_newsFragment, bundle)
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

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = adapter.listNews[position]
                DeleteDialog.showDialog(
                    context as AppCompatActivity,
                    object : DeleteDialog.Listener {
                        override fun onClick() {
                            viewModel.delete(article)
                        }
                    })
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }

        viewModel.getAllNews().observe(viewLifecycleOwner) { articles ->
            adapter.setList(articles)
        }
    }

    private fun setupRecyclerView() {
        binding.rvSavedNews.adapter = adapter
        binding.rvSavedNews.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dellete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        DeleteDialog.showDialog(context as AppCompatActivity, object : DeleteDialog.Listener {
            override fun onClick() {
                viewModel.deleteAll()
            }
        })
    }
}