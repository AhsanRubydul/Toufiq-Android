package com.shahcement.tawfiq.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.shahcement.tawfiq.app.App

object PreferenceHelper {

    private val prefs = App.instance.getSharedPreferences(PrefConstants.PREF_NAME, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String ?: "") as T
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun put(key: String, value: Any?) {
        prefs[key] = value
    }

    fun getString(key: String, defaultValue: String? = null): String {
        return prefs[key, defaultValue]
    }

    fun getInt(key: String, defaultValue: Int? = null): Int {
        return prefs[key, defaultValue]
    }

    fun getBoolean(key: String, defaultValue: Boolean? = null): Boolean {
        return prefs[key, defaultValue]
    }

    fun getFloat(key: String, defaultValue: Float? = null): Float {
        return prefs[key, defaultValue]
    }

    fun getLong(key: String, defaultValue: Long? = null): Long {
        return prefs[key, defaultValue]
    }

    fun setPrefsChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }
}