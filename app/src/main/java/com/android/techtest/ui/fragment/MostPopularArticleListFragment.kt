package com.android.techtest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.techtest.R
import com.android.techtest.databinding.FragmentMostPopulerArticleListBinding
import com.android.techtest.domain.util.Status
import com.android.techtest.ui.adapter.ArticleListAdapter
import com.android.techtest.util.showOrGone
import com.android.techtest.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MostPopularArticleListFragment : Fragment() {

    private val articleViewModel by viewModel<ArticleViewModel>()
    private lateinit var binding: FragmentMostPopulerArticleListBinding
    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMostPopulerArticleListBinding.inflate(
        inflater,
        container,
        false
    ).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        articleViewModel.fetchArticleList()
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
            Navigation.findNavController(binding.recArticle)
                .navigate(
                    R.id.action_mostPopulerArticleListFragment_to_articleDetailsFragment,
                    bundleOf(Pair(KEY_DETAIL_DATA, resultItem))
                )
        }
    }

    override fun onResume() {
        super.onResume()
        setObserver()
    }

    private fun setObserver() {
        articleViewModel.articleList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    with(binding) {
                        txtNoData.showOrGone(false)
                        adapter.differ.submitList(it.data?.results)
                        recArticle.showOrGone(true)
                        progressbar.showOrGone(false)
                    }
                }
                Status.LOADING -> {
                        binding.progressbar.showOrGone(true)
                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        binding.progressbar.showOrGone(false)
                }
            }
        }
    }

    companion object{
        const val KEY_DETAIL_DATA = "KEY_DETAIL_DATA"
    }
}