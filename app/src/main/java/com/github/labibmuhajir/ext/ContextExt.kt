package com.github.labibmuhajir.ext

import android.content.Context


fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()


        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        String(buffer, Charsets.UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()

        null
    }
}