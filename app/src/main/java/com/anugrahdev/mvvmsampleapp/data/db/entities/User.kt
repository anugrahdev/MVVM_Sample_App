package com.anugrahdev.mvvmsampleapp.data.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_USER_ID =0;

@Entity
data class User(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid : Int = CURRENT_USER_ID
}