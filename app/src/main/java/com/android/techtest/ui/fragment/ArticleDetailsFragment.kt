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
import com.android.techtest.data.entities.Result
import com.android.techtest.databinding.FragmentArticleDetailsBinding
import com.android.techtest.util.Constants.KEY_DETAIL_DATA

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
        setClickable()
    }

    private fun getData() {
        binding.data = if (Build.VERSION.SDK_INT >= 33) {
            arguments?.getParcelable(KEY_DETAIL_DATA, Result::class.java)
        } else {
            arguments?.getParcelable(KEY_DETAIL_DATA)
        }
    }

    private fun setClickable() {
        with(binding) {
            txtReadMore.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse(binding.data?.url)).apply {
                    startActivity(this)
                }
            }
        }
    }
}