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

    fun showNumberInBangla(x: Int): String {
        val strBuilder = StringBuilder()
        val enBnDiff = 2486

        x.toString().forEach { letter ->
            try {
                if (letter.toInt() in 48..57) {
                    val replacementLetterCode = letter.toInt() + enBnDiff
                    strBuilder.append(replacementLetterCode.toChar())
                } else {
                    strBuilder.append(letter)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return strBuilder.toString()
    }
}