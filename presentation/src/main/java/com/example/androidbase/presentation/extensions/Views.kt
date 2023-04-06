package com.example.androidbase.presentation.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.androidbase.R
import com.example.androidbase.databinding.ToolbarViewBinding


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ImageView.changeDrawableColor(color: Int) {
    this.setColorFilter(resources.getColor(color))
}

fun View.click(click: () -> Unit) {
    setOnClickListener { click() }
}

fun View.getColor(@ColorRes color: Int): Int {
    return this.context.resources.getColor(color)
}

fun View.navigate(action: NavDirections) {
    findNavController().navigate(action)
}

fun View.takeScreenshot(): Bitmap {
    val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val bgDrawable = this.background
    if (bgDrawable != null) {
        bgDrawable.draw(canvas)
    } else {
        canvas.drawColor(Color.WHITE)
    }
    this.draw(canvas)
    return bitmap
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


fun Fragment.configure(toolbar: ToolbarViewBinding, title: String = "") {
    toolbar.apply {
        toolbarBack.setOnClickListener {
            findNavController().popBackStack()
        }
        toolbarTitle.text = title
    }
}



