package com.android.techtest.ui

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.techtest.domain.entities.MediaCharacter
import com.bumptech.glide.Glide

object ArticleView {

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, list: List<MediaCharacter>) {
        if(list.isNotEmpty() && list[0].mediaMetadata.isNotEmpty()){
            Glide.with(view.context)
                .load(Uri.parse(list[0].mediaMetadata[list[0].mediaMetadata.size-1].url))
                .into(view)
        }
    }

}