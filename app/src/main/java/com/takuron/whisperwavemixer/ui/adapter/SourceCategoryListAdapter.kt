package com.takuron.whisperwavemixer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.takuron.whisperwavemixer.R
import com.takuron.whisperwavemixer.application
import com.takuron.whisperwavemixer.data.source.SourceCategoryData
import com.takuron.whisperwavemixer.databinding.ItemSourcepageCategoryBinding

class SourceCategoryListAdapter(var list:List<SourceCategoryData>) : RecyclerView.Adapter<SourceCategoryListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SourceCategoryListViewHolder = SourceCategoryListViewHolder(
        ItemSourcepageCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SourceCategoryListViewHolder, position: Int) {
        holder.binding.titleTextView.text = list[position].name
        holder.binding.subtitleTextView.text = application.getString(R.string.source_category_list_items_subtitle).format(list[position].size)
    }
}

class SourceCategoryListViewHolder(val binding: ItemSourcepageCategoryBinding) : ViewHolder(binding.root)