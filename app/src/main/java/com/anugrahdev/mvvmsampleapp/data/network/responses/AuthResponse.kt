package com.anugrahdev.mvvmsampleapp.data.network.responses


import com.anugrahdev.mvvmsampleapp.data.db.entities.User
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("isSuccessful")
    val isSuccessful: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: User?
)