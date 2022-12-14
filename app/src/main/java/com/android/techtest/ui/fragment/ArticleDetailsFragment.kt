package com.android.techtest.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.android.techtest.databinding.FragmentArticleDetailsBinding
import com.android.techtest.domain.entities.ResultCharacter
import com.android.techtest.entities.ResultData
import com.android.techtest.ui.ArticleView
import com.android.techtest.ui.fragment.MostPopularArticleListFragment.Companion.KEY_DETAIL_DATA

class ArticleDetailsFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentArticleDetailsBinding.inflate(
        inflater,
        container,
        false
    ).also {
        binding = it
    }.root

    @RequiresApi(33)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    @Suppress("DEPRECATION")
    private fun getData() {
        val data = if (Build.VERSION.SDK_INT >= 33) {
            requireArguments().getParcelable(KEY_DETAIL_DATA, ResultData::class.java)
        } else {
            requireArguments().getParcelable(KEY_DETAIL_DATA)
        }

        with(binding) {
            data?.run {
                txtTitle.text = title
                txtByName.text = byline
                txtDate.text = publishedDate
                txtDescription.text = abstract
                ArticleView.loadImage(imgBanner, media[0].mediaMetadata[0].url)
                txtReadMore.setOnClickListener {
                    Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                        startActivity(this)
                    }
                }
            }
        }
    }
}