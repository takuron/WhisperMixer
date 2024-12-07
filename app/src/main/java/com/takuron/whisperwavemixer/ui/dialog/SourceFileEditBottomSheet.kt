package com.takuron.whisperwavemixer.ui.dialog

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.takuron.whisperwavemixer.data.source.SourceFileData
import com.takuron.whisperwavemixer.databinding.BottomsheetEditsourceBinding
import java.io.File

class SourceFileEditBottomSheet(val data:SourceFileData) : BottomSheetDialogFragment() {
    val player by lazy { ExoPlayer.Builder(requireContext()).build() }

    private val binding by lazy { BottomsheetEditsourceBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val mediaItem = MediaItem.fromUri(Uri.fromFile(File(data.file_path)))
        player.setMediaItem(mediaItem)
        player.prepare()

        binding.playerView.player = player

        return binding.root
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }
}