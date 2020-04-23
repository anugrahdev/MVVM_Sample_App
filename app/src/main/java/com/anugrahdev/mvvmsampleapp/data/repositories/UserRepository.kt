package com.anugrahdev.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anugrahdev.mvvmsampleapp.data.db.AppDatabase
import com.anugrahdev.mvvmsampleapp.data.db.entities.User
import com.anugrahdev.mvvmsampleapp.data.network.ApiService
import com.anugrahdev.mvvmsampleapp.data.network.SafeApiRequest
import com.anugrahdev.mvvmsampleapp.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val api:ApiService, private val db:AppDatabase) : SafeApiRequest() {

    suspend fun userLogin(email :String, password:String) : AuthResponse{

        return apiRequest{ api.userLogin(email,password) }

    }

    suspend fun userSignup(email:String,password:String,name:String):AuthResponse{
        return apiRequest{ api.userSignup(email,password,name) }
    }
    
    suspend fun saveUser(user : User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}