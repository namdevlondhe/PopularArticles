package com.android.techtest.ui.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.techtest.R
import com.android.techtest.databinding.ListItemArticalListBinding
import com.android.techtest.entities.ResultData
import com.bumptech.glide.Glide

class ArticleViewHolder(
    parent: View,
    private val onItemClick: ((ResultData) -> Unit)?
) : RecyclerView.ViewHolder(parent) {

    private val binding = ListItemArticalListBinding.bind(parent)

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    fun bindTo(item: ResultData) {
        with(binding) {
            item.run {
                txtTitle.text = title
                txtByLine.text = byline
                txtDate.text = " " + publishedDate
                txtSource.text = section
                if (media.isNotEmpty() && media[0].mediaMetadata.isNotEmpty()) {
                    Glide.with(imageView.context)
                        .load(Uri.parse(media[0].mediaMetadata[0].url))
                        .placeholder(imageView.context.getDrawable(R.drawable.ic_launcher_foreground))
                        .into(imageView)
                }
                container.setOnClickListener {
                    onItemClick?.invoke(this)
                }
            }
        }
    }
}