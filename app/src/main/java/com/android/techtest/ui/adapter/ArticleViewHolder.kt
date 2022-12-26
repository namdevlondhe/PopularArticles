package com.android.techtest.ui.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.techtest.R
import com.android.techtest.data.entities.Result
import com.android.techtest.databinding.ListItemArticalListBinding
import com.bumptech.glide.Glide

class ArticleViewHolder(
    parent: View,
    private val onItemClick: ((Result) -> Unit)?
) : RecyclerView.ViewHolder(parent) {

    private val binding = ListItemArticalListBinding.bind(parent)

    fun bindTo(item: Result) {
        with(binding) {
            txtTitle.text = item.title
            txtByLine.text = item.byline
            txtDate.text = " " + item.publishedDate
            txtSource.text = item.section
            if (item.media.isNotEmpty() && item.media[0].mediaMetadata.isNotEmpty()) {
                Glide.with(imageView.context)
                    .load(Uri.parse(item.media[0].mediaMetadata[0].url))
                    .placeholder(imageView.context.getDrawable(R.drawable.ic_launcher_foreground))
                    .into(imageView)
            }
            container.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}