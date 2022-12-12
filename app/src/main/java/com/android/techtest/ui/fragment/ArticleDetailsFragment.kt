package com.android.techtest.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.techtest.databinding.FragmentArticleDetailsBinding
import com.android.techtest.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ArticleDetailsFragment():Fragment() {

    private val viewModel by sharedViewModel<ArticleViewModel>()

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater,container,false)
        binding.data = viewModel.dataItem
        setClickable()
        return binding.root
    }

    private fun setClickable() {
        binding.txtReadMore.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.dataItem.url)).apply {
                startActivity(this)
            }
        }
    }
}