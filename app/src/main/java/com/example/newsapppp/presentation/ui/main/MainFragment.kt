package com.example.newsapppp.presentation.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapppp.R
import com.example.newsapppp.databinding.FragmentMainBinding
import com.example.newsapppp.presentation.ui.adapters.NewsAdapter
import com.example.newsapppp.presentation.ui.base.BaseFragment
import com.example.newsapppp.presentation.utils.Resource
import com.example.newsapppp.presentation.utils.USA
import com.example.newsapppp.presentation.utils.categories
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {
    private val newsAdapter by lazy { NewsAdapter() }
    private lateinit var sharedPref: SharedPreferences
    override val viewModel by viewModels<MainFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        sharedPreferencesSaveCountryAndCategory()
        showNews()

        btProfile.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        newsAdapter.setOnItemClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewsFragment(it))
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            startLayoutAnimation()
        }
    }



    fun sharedPreferencesSaveCountryAndCategory(){
        sharedPref = requireActivity().getSharedPreferences("Table", Context.MODE_PRIVATE)
        val country = sharedPref.getString("country",USA)!!
        viewModel.getNews(countryCode = country,category = categories[0])
        tvCountry.text = country

        binding.tabMain.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//
                viewModel.getNews(countryCode = country,categories[tab!!.position])
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }




    fun showNews(){
        viewModel.newsResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    hideProgressBar()
                    it.data?.let {
                        newsAdapter.setList(it.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    it.data?.let {

                        Log.e("checkData", "MainFragment: error:${it}")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.INVISIBLE
        tvCenterText.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
        tvCenterText.visibility = View.VISIBLE
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)

}
