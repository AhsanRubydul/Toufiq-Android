package com.shahcement.toufiq

import android.content.res.AssetManager
import java.io.IOException
import java.nio.charset.Charset

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
}