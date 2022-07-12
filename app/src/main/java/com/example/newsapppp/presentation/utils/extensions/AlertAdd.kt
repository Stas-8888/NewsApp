package com.example.newsapppp.presentation.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapppp.R
import com.tapadoo.alerter.Alerter

fun Fragment.showAlertAdd() {

    activity?.let {
        Alerter.create(it)
            .setTitle("Статья Добавлена")
            .setIcon(R.drawable.ic_breaking_news)
            .setBackgroundColorRes(R.color.colorRed)
            .setDuration(2000)
            .setOnClickListener(View.OnClickListener {

            })
            .show()
    }
}

