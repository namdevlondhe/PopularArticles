package com.android.techtest.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.techtest.databinding.FragmentArticleDetailsBinding
import com.android.techtest.domain.usecases.GetArticleUseCases
import com.android.techtest.viewmodel.ArticleViewModel

class ArticleDetailsFragment : Fragment() {

    private lateinit var viewModel: ArticleViewModel

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)

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
        binding.data = viewModel.dataItem
        setClickable()

    }

    private fun setClickable() {
        with(binding) {
            txtReadMore.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.dataItem.url)).apply {
                    startActivity(this)
                }
            }
        }
    }
}