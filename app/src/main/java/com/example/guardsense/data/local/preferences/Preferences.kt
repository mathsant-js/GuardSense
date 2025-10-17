package com.example.guardsense.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

class Preferences(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun setColorTheme(chosenTheme: String) {
        prefs.edit().putString("colorTheme", chosenTheme).apply()
    }

    fun getColorTheme(): String {
        return prefs.getString("colorTheme", "light") ?: "light"
    }
}