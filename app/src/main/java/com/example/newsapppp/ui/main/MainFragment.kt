package com.example.newsapppp.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapppp.Application
import com.example.newsapppp.R
import com.example.newsapppp.ui.adapters.NewsAdapter
import com.example.newsapppp.databinding.FragmentMainBinding
import com.example.newsapppp.dialog.DeleteDialog


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    private val adapter by lazy { NewsAdapter() }
    private val viewModel: MainFragmentViewModel by activityViewModels {
        MainFragmentViewModel.MainViewModelFactory((context?.applicationContext as Application).database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
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
            findNavController().navigate(R.id.action_mainFragment_to_newsFragment, bundle)
        }
    }

    private fun setupRecyclerView() {
        viewModel.getNewsRetrofit()
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())

        viewModel.myNews.observe(viewLifecycleOwner){
            adapter.setList(it.body()!!.articles)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_account) {
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        DeleteDialog.showDialog(context as AppCompatActivity, object : DeleteDialog.Listener {
            override fun onClick() {
//                viewModel.deleteAll()
            }
        })
    }
}