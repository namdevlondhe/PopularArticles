package com.android.techtest.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.techtest.R
import com.android.techtest.data.entities.Result

class ArticleListAdapter : RecyclerView.Adapter<ArticleViewHolder>() {

    var onItemClick: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.list_item_artical_list,
                    parent,
                    false
                ), onItemClick
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bindTo(differ.currentList[position])

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem

    }
    val differ = AsyncListDiffer(this, differCallback)
}