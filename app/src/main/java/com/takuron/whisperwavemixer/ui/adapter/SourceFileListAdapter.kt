package com.takuron.whisperwavemixer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.takuron.whisperwavemixer.data.source.SourceFileData
import com.takuron.whisperwavemixer.databinding.ItemSourcepageFileBinding

class SourceFileListAdapter (var list:List<SourceFileData>) : RecyclerView.Adapter<SourceFileListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SourceFileListViewHolder = SourceFileListViewHolder(
        ItemSourcepageFileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SourceFileListViewHolder, position: Int) {
        holder.binding.titleTextView.text = list[position].title
        holder.binding.subtitleTextView.text = (list[position].file_size.toDouble()/1000/1000).toString()
    }
}

class SourceFileListViewHolder(val binding: ItemSourcepageFileBinding) : ViewHolder(binding.root)