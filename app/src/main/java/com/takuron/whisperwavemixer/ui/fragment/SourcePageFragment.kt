package com.takuron.whisperwavemixer.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.takuron.whisperwavemixer.R
import com.takuron.whisperwavemixer.application
import com.takuron.whisperwavemixer.databinding.FragmentSourcepageBinding
import com.takuron.whisperwavemixer.ui.dialog.TextInputDialog
import com.takuron.whisperwavemixer.ui.adapter.SourceCategoryListAdapter
import com.takuron.whisperwavemixer.ui.adapter.SourceFileListAdapter
import com.takuron.whisperwavemixer.utils.ViewUtils
import com.takuron.whisperwavemixer.viewmodel.SourcePageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class SourcePageFragment : Fragment() {
    private val categoryListAdapter by lazy { SourceCategoryListAdapter(ArrayList()) }
    private val fileListAdapter by lazy { SourceFileListAdapter(ArrayList()) }

    private val sourceLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

            if (result.data?.clipData != null) {
                result.data?.clipData?.let { data: ClipData ->
                    lifecycleScope.launch(Dispatchers.Main) {
                        for (i in 0 until data.itemCount) {
                            viewModel.presenter.saveSource(this, data.getItemAt(i).uri)
                                .join()
                        }
                        Toast.makeText(application, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            } else
                result.data?.data?.let { uri: Uri ->
                    lifecycleScope.launch(Dispatchers.Main) {
                        viewModel.presenter.saveSource(this, uri).join()
                        Toast.makeText(application, "Success", Toast.LENGTH_SHORT).show()
                    }
                }

        }

    private val binding by lazy { FragmentSourcepageBinding.inflate(layoutInflater) }
    private val viewModel: SourcePageViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        binding.categoryRecyclerView.adapter = categoryListAdapter
        binding.categoryRecyclerView.layoutManager = GridLayoutManager(context,2)

        binding.fileRecyclerView.adapter = fileListAdapter
        binding.fileRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fileRecyclerView.addItemDecoration(MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
            dividerInsetStart = ViewUtils.dp2px(16f).roundToInt()
            dividerInsetEnd = ViewUtils.dp2px(16f).roundToInt()
            isLastItemDecorated = false
        })

        viewModel.categoryListLiveData.observe(viewLifecycleOwner){
            if(it.isNotEmpty() || viewModel.fileListLiveData.value?.isEmpty() == false) {
                binding.nodataTextView.visibility = View.GONE
                binding.nodataImageView.visibility = View.GONE
                binding.fatherScroll.visibility = View.VISIBLE
            }else{
                binding.nodataTextView.visibility = View.VISIBLE
                binding.nodataImageView.visibility = View.VISIBLE
                binding.fatherScroll.visibility = View.GONE
            }

            categoryListAdapter.list = it
            categoryListAdapter.notifyDataSetChanged()

        }
        viewModel.fileListLiveData.observe(viewLifecycleOwner){
            if(it.isNotEmpty() || viewModel.categoryListLiveData.value?.isEmpty() == false) {
                binding.nodataTextView.visibility = View.GONE
                binding.nodataImageView.visibility = View.GONE
                binding.fatherScroll.visibility = View.VISIBLE
            }else{
                binding.nodataTextView.visibility = View.VISIBLE
                binding.nodataImageView.visibility = View.VISIBLE
                binding.fatherScroll.visibility = View.GONE
            }

            fileListAdapter.list = it
            fileListAdapter.notifyDataSetChanged()

        }


        binding.addSourceFab.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.setType("audio/*")
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            sourceLauncher.launch(intent)
        }

        binding.makeCategoryFab.setOnClickListener {
            TextInputDialog(
                titleStringId = R.string.source_dialog_new_title,
                hintStringId = R.string.source_dialog_new_hint,
                submitButtonStringId = R.string.app_dialog_save,
                cancelButtonStringId = R.string.app_dialog_cancel
            ) { _, _, text ->
                lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.presenter.newCategory(this,text.toString())
                }
            }.show(childFragmentManager, "new_category")
        }

        return binding.root
    }
}