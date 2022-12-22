package com.android.techtest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.techtest.R
import com.android.techtest.databinding.FragmentMostPopulerArticleListBinding
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.domain.util.Status
import com.android.techtest.ui.adapter.ArticleListAdapter
import com.android.techtest.viewmodel.ArticleViewModel

class MostPopularArticleListFragment : Fragment() {

    private lateinit var viewModel: ArticleViewModel

    private lateinit var binding: FragmentMostPopulerArticleListBinding
    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMostPopulerArticleListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = requireActivity().run {
            ViewModelProvider(
                this,
                ArticleViewModel.Factory(GetArticleUseCases())
            )[ArticleViewModel::class.java]
        }
        init()
        viewModel.fetchArticleList()

    }

    private fun init() {
        adapter = ArticleListAdapter()
        with(binding) {
            recArticle.adapter = adapter
            val layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            recArticle.layoutManager = layoutManager
        }

        adapter.onItemClick = { resultItem ->
            viewModel.setCurrentArticle(resultItem)
            Navigation.findNavController(binding.recArticle)
                .navigate(R.id.action_mostPopulerArticleListFragment_to_articleDetailsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        setObserver()
    }

    private fun setObserver() {
        viewModel.articleList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    with(binding){
                    txtNoData.visibility = View.GONE
                    adapter.updateData(it.data?.results ?: listOf())
                    recArticle.visibility = View.VISIBLE
                    progressbar.visibility = View.GONE
                    }
                }
                Status.LOADING -> {
                    with(binding) {
                        progressbar.visibility = View.VISIBLE
                    }
                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    with(binding) {
                        progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

}