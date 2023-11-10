package com.example.viajaplus.services

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceService(private val context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SingletonData.PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun get(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getboolean(key: String): Boolean {
        return sharedPreferences. getBoolean(key, true)
    }

    fun post(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun post(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun delete(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}
