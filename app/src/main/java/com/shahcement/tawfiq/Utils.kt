package com.shahcement.tawfiq

import android.content.res.AssetManager
import java.io.IOException
import java.nio.charset.Charset
import java.text.NumberFormat
import java.util.*

object Utils {
    fun loadJSONFromAsset(assetManager: AssetManager, fileName: String): String? {
        return try {
            val inputStream = assetManager.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    fun showNumberInBangla(x: Int): String? {
        val numberFormatter = NumberFormat.getNumberInstance(Locale("bn", "BD"))
        return numberFormatter.format(x.toLong())
    }
}