package com.anugrahdev.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.anugrahdev.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)
}