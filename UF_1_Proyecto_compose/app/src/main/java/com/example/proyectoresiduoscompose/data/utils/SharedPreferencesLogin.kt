package com.example.proyectoresiduoscompose.data.utils

import android.content.Context
import android.content.SharedPreferences

//I create a singleton instance of shared preferences to get the current loged in user
object SharedPreferencesLogin{
    private const val PREFS_NAME = "login_prefs"
    private const val KEY_USER_ID = "user_id"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(context: Context, userId: Int) {
        val prefs = getSharedPreferences(context)
        val editor = prefs.edit()
        editor.putInt(KEY_USER_ID, userId)
        editor.apply()
    }

    fun getUserId(context: Context): Int {
        val prefs = getSharedPreferences(context)
        return prefs.getInt(KEY_USER_ID, -1)  // Default is -1 if not found
    }

    fun clearUser(context: Context) {
        val prefs = getSharedPreferences(context)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}