package com.takuron.whisperwavemixer.ui.source

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.takuron.whisperwavemixer.application
import com.takuron.whisperwavemixer.databinding.FragmentSourceBinding
import com.takuron.whisperwavemixer.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SourceFragment : Fragment() {

    val sourcceLaubcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

            if (result.data?.clipData != null) {

            } else
                result.data?.data?.let { uri:Uri ->
                    lifecycleScope.launch(Dispatchers.Main) {
                        sourceViewModel.presenter.saveSource(this,uri).join()
                        Toast.makeText(application,"Success",Toast.LENGTH_SHORT).show()
                    }


//                    val cursor: Cursor? = application.getContentResolver()
//                        .query(this, AUDIO_INFO, null, null, null, null)
//                    if (cursor != null && cursor.moveToFirst()) {
//                        val displayName =
//                            cursor.getString(cursor.getColumnIndexOrThrow(AUDIO_INFO.get(0)))
//                        val size =
//                            cursor.getString(cursor.getColumnIndexOrThrow(AUDIO_INFO.get(1)))
//
//                        Toast.makeText(application, "$displayName $size", Toast.LENGTH_SHORT).show()
//                    }
//                    cursor?.close()
                }

        }

    private val binding by lazy { FragmentSourceBinding.inflate(layoutInflater) }
    val sourceViewModel: SourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.extendedFloatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.setType("audio/*")
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            sourcceLaubcher.launch(intent)
        }

        return binding.root
    }
}