package com.android.techtest.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.techtest.model.ArticleResponse
import com.android.techtest.model.Media
import com.android.techtest.util.Resource
import com.bumptech.glide.Glide

object ArticleView {
    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<ArticleResponse>?) {

    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, list: List<Media>) {
        if(list.isNotEmpty() && list[0].mediaMetadata.isNotEmpty()){
            val index = list[0].mediaMetadata.size
            Glide.with(view.context)
                .load(Uri.parse(list[0].mediaMetadata[index-1].url))
                .into(view)
        }
    }
}