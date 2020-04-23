package com.anugrahdev.mvvmsampleapp.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private var KEY_SAVED_AT = "key_saved_at"

class PreferenceProviders(
    context:Context
) {
    private val appContext = context.applicationContext
    private val preference:SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun savelastSavedAt(SavedAt:String){
        preference.edit().putString(
            KEY_SAVED_AT,
            SavedAt
        ).apply()
    }

    fun getLastSavedAt():String?{
        return preference.getString(KEY_SAVED_AT,null)
    }
}