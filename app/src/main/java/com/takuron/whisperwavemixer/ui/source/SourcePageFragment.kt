package com.takuron.whisperwavemixer.ui.source

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
import com.takuron.whisperwavemixer.R
import com.takuron.whisperwavemixer.application
import com.takuron.whisperwavemixer.databinding.FragmentSourcepageBinding
import com.takuron.whisperwavemixer.ui.dialog.TextInputDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SourcePageFragment : Fragment() {

    private val sourceLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

            if (result.data?.clipData != null) {
                result.data?.clipData?.let { data: ClipData ->
                    lifecycleScope.launch(Dispatchers.Main) {
                        for (i in 0 until data.itemCount) {
                            sourcePageViewModel.presenter.saveSource(this, data.getItemAt(i).uri)
                                .join()
                        }
                        Toast.makeText(application, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            } else
                result.data?.data?.let { uri: Uri ->
                    lifecycleScope.launch(Dispatchers.Main) {
                        sourcePageViewModel.presenter.saveSource(this, uri).join()
                        Toast.makeText(application, "Success", Toast.LENGTH_SHORT).show()
                    }
                }

        }

    private val binding by lazy { FragmentSourcepageBinding.inflate(layoutInflater) }
    private val sourcePageViewModel: SourcePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
            ) { dialog, id, text ->
                lifecycleScope.launch(Dispatchers.Main) {
                    sourcePageViewModel.presenter.newCategory(this,text.toString())
                }
            }.show(childFragmentManager, "new_category")
        }

        return binding.root
    }
}