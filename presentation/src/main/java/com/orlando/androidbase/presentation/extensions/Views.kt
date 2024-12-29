package com.orlando.androidbase.presentation.extensions

import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.orlando.androidbase.R


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


fun View.click(click: () -> Unit) {
    setOnClickListener { click() }
}


fun ImageView.loadUrl(url: String?) {
    url?.let {
        val circularProgressDrawable = CircularProgressDrawable(this.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(this.context).load(url).placeholder(circularProgressDrawable)
            .error(R.drawable.startwars).into(this)
    }
}



