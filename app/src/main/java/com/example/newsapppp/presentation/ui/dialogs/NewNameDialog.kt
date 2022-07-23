package com.example.newsapppp.presentation.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.newsapppp.databinding.NewNameDialogBinding

object NewNameDialog {
    fun showDialog(context: Context, listener: Listener, name:String){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = NewNameDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            edNewListName.setText(name)
            if (name.isNotEmpty()) bCreate.text = "Update"
            bCreate.setOnClickListener {
                val listName = edNewListName.text.toString()
                if(listName.isNotEmpty()){
                    listener.onClick(listName)
                }
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface Listener {
        fun onClick(name:String)
    }

}
