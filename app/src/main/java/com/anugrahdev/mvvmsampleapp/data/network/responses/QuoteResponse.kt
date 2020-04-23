package com.anugrahdev.mvvmsampleapp.data.network.responses


import com.anugrahdev.mvvmsampleapp.data.db.entities.Quote
import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("isSuccessful")
    val isSuccessful: Boolean,
    @SerializedName("quotes")
    val quotes: List<Quote>
)