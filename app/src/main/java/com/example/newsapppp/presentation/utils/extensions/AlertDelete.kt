package com.example.newsapppp.presentation.utils.extensions

import android.app.Dialog
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapppp.R
import com.tapadoo.alerter.Alerter

fun Fragment.showAlertDelete() {

    activity?.let {
        Alerter.create(it)
            .setTitle("Статья Удалена")
            .setIcon(R.drawable.ic_delete)
            .setBackgroundColorRes(R.color.colorRed)
            .setDuration(2000)
            .setOnClickListener(View.OnClickListener {

            })
            .show()
    }
}
//    Alerter.create(Dialog(requireContext()))
//        .setTitle("Alert Title")
//        .setText("Alert text...")
//        .setIcon(R.drawable.ic_delete)
//        .setBackgroundColorRes(R.color.colorRed)
//        .setDuration(2000)
//        .setOnClickListener(View.OnClickListener {
//
//        })
//        .show()
//
//
//}
