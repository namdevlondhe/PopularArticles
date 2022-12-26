package com.android.techtest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.techtest.R
import com.android.techtest.data.entities.Result
import com.android.techtest.databinding.FragmentMostPopulerArticleListBinding
import com.android.techtest.domain.util.Status
import com.android.techtest.ui.adapter.ArticleListAdapter
import com.android.techtest.util.Constants.KEY_DETAIL_DATA
import com.android.techtest.util.ViewUtils.showOrGone
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
            val bundle = Bundle()
            bundle.putParcelable(KEY_DETAIL_DATA, resultItem)
            Navigation.findNavController(binding.recArticle)
                .navigate(
                    R.id.action_mostPopulerArticleListFragment_to_articleDetailsFragment,
                    bundle
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
                        adapter.differ.submitList(it.data?.results as List<Result>)
                        recArticle.showOrGone(true)
                        progressbar.showOrGone(false)
                    }
                }
                Status.LOADING -> {
                    with(binding) {
                        progressbar.showOrGone(true)
                    }
                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    with(binding) {
                        progressbar.showOrGone(false)
                    }
                }
            }
        }
    }

}