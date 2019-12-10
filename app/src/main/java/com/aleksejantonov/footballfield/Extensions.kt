package com.aleksejantonov.footballfield

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver

fun <T : View> T.onGlobalLayout(body: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            body()
        }
    })
}

fun Context.getPxFromDp(dpValue: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), resources.displayMetrics
    )
        .toInt()
}