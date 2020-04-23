package com.anugrahdev.mvvmsampleapp.data.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val quote: String?=null,
    val author: String?=null,
    val thumbnail: String?=null,
    val created_at: String?=null,
    val updated_at: String?=null
)