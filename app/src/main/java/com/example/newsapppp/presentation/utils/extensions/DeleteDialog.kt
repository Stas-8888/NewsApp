package com.example.newsapppp.presentation.utils.extensions

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.newsapppp.databinding.DeleteDialog2Binding
import com.example.newsapppp.databinding.DeleteDialog3Binding
import com.example.newsapppp.databinding.DeleteDialogBinding

fun Fragment.showDeleteDialog(onSuccess: () -> Unit, noteSuccess: () -> Unit) {
    var dialog: AlertDialog? = null
    val builder = AlertDialog.Builder(context)
    val binding = DeleteDialog3Binding.inflate(LayoutInflater.from(context))

    builder.setView(binding.root)
    binding.apply {
        bDelete.setOnClickListener {
            onSuccess.invoke()
            dialog?.dismiss()
        }
        bCansel.setOnClickListener {
            noteSuccess.invoke()
            dialog?.dismiss()
        }
    }
    dialog = builder.create()
    dialog.window?.setBackgroundDrawable(null)
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.show()
}
