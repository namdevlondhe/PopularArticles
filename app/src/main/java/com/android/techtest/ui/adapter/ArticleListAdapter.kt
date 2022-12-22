package com.android.techtest.ui.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.techtest.R
import com.android.techtest.databinding.ListItemArticalListBinding
import com.android.techtest.domain.entities.Result
import com.bumptech.glide.Glide

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    private val dataList: MutableList<Result> = mutableListOf()
    var onItemClick: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_artical_list, parent, false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bindTo(dataList[position])

    fun updateData(items: List<Result>) {
        dataList.clear()
        dataList.addAll(items)
        notifyDataSetChanged()
        Log.i("Inside updateData ","" + dataList.size)
    }

    inner class ArticleViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        private val binding = ListItemArticalListBinding.bind(parent)

        fun bindTo(item: Result) {
            binding.txtTitle.text = item.title
            binding.txtByLine.text = item.byline
            binding.txtDate.text = " "+item.publishedDate
            binding.txtSource.text = item.section
            if(item.media.isNotEmpty() && item.media[0].mediaMetadata.isNotEmpty()){
                Glide.with(binding.imageView.context)
                    .load(Uri.parse(item.media[0].mediaMetadata[0].url))
                    .placeholder(binding.imageView.context.getDrawable(R.drawable.ic_launcher_foreground))
                    .into(binding.imageView)
            }
            binding.container.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }
}