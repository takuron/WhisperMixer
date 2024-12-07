package com.takuron.whisperwavemixer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.takuron.whisperwavemixer.data.source.SourceFileData
import com.takuron.whisperwavemixer.databinding.ItemSourcepageFileBinding
import com.takuron.whisperwavemixer.ui.dialog.SourceFileEditBottomSheet
import com.takuron.whisperwavemixer.utils.file.FileTextUtils
import java.lang.ref.WeakReference

class SourceFileListAdapter (var list:List<SourceFileData>) : RecyclerView.Adapter<SourceFileListViewHolder>() {
    var fragmentManager:WeakReference<FragmentManager> = WeakReference(null)

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
        holder.binding.subtitleTextView.text = FileTextUtils.size4Display(list[position].file_size)

        holder.binding.fatherItemView.setOnClickListener {
            fragmentManager.get()?.let {  it1 -> SourceFileEditBottomSheet(list[holder.bindingAdapterPosition]).show(it1,"file_edit") }
        }

    }
}

class SourceFileListViewHolder(val binding: ItemSourcepageFileBinding) : ViewHolder(binding.root)