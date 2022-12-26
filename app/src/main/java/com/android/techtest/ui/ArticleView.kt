package com.android.techtest.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.techtest.data.entities.Media
import com.bumptech.glide.Glide

object ArticleView {

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, list: List<Media>) {
        if(list.isNotEmpty() && list[0].mediaMetadata?.isNotEmpty() == true){
            val index = list[0].mediaMetadata?.size
            if (index != null) {
                Glide.with(view.context)
                    .load(Uri.parse(list[0].mediaMetadata?.get(index-1)?.url))
                    .into(view)
            }
        }
    }

}