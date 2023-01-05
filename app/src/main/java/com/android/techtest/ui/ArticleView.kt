package com.android.techtest.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.techtest.domain.entities.MediaCharacter
import com.bumptech.glide.Glide

object ArticleView {

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, url: String) {
        if(url.isNotEmpty()){
            Glide.with(view.context)
                .load(Uri.parse(url))
                .into(view)
        }
    }

}