package com.anugrahdev.mvvmsampleapp.data.network

import com.anugrahdev.mvvmsampleapp.data.network.responses.AuthResponse
import com.anugrahdev.mvvmsampleapp.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("email") email:String,
        @Field("password") password: String,
        @Field("name") name:String
    ):Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes():Response<QuoteResponse>

    companion object{
        operator fun invoke(networkInterceptor: NetworkInterceptor): ApiService{

            val okHttpClient = OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5,TimeUnit.MINUTES)
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}