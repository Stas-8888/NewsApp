package com.example.newsapppp.presentation.utils

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.newsapppp.databinding.DeleteDialogBinding


fun Fragment.showDeleteDialog(onSuccess: () -> Unit) {
    var dialog: AlertDialog? = null
    val builder = AlertDialog.Builder(context)
    val binding = DeleteDialogBinding.inflate(LayoutInflater.from(context))
    builder.setView(binding.root)
    binding.apply {
        bDelete.setOnClickListener {
            onSuccess.invoke()
            dialog?.dismiss()
        }
        bCansel.setOnClickListener {
            dialog?.dismiss()
        }
    }
    dialog = builder.create()
    dialog.window?.setBackgroundDrawable(null)
    dialog.show()
}
