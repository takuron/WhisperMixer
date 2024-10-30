package com.takuron.whisperwavemixer.ui.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takuron.whisperwavemixer.databinding.FragmentSourceBinding

class SourceFragment : Fragment() {

    private val binding by lazy { FragmentSourceBinding.inflate(layoutInflater) }
    val sourceViewModel:SourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}