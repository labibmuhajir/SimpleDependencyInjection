package com.github.labibmuhajir.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes view: Int): View {
    return LayoutInflater.from(context).inflate(view, this, false)
}