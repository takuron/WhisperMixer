package com.takuron.whisperwavemixer.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.takuron.whisperwavemixer.databinding.DialogTextinputBinding

class TextInputDialog(
    val titleStringId:Int,
    val hintStringId:Int,
    val submitButtonStringId:Int,
    val cancelButtonStringId:Int,
    val inputListener: (dialogInterface:DialogInterface,id:Int,text:String) -> Unit,
) : DialogFragment() {
    private val binding by lazy { DialogTextinputBinding.inflate(layoutInflater) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding.textField.setHint(hintStringId)
        binding.editText.addTextChangedListener {
            if(it.isNullOrEmpty())
                (dialog as AlertDialog?)?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = false
            else
                (dialog as AlertDialog?)?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = true
        }

        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)
            builder.apply {
                setTitle(titleStringId)
                setView(binding.root)
                setPositiveButton(submitButtonStringId){ dialog, id ->
                    inputListener(dialog,id,binding.editText.text.toString())
                }
                setNegativeButton(cancelButtonStringId){ _, _ -> }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onResume() {
        super.onResume()
        if(binding.editText.text.isNullOrEmpty())
            (dialog as AlertDialog?)?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = false
        else
            (dialog as AlertDialog?)?.getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = true
    }
}