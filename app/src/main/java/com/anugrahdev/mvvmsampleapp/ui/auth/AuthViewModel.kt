package com.anugrahdev.mvvmsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.anugrahdev.mvvmsampleapp.data.repositories.UserRepository
import com.anugrahdev.mvvmsampleapp.utils.ApiException
import com.anugrahdev.mvvmsampleapp.utils.Coroutines
import com.anugrahdev.mvvmsampleapp.utils.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var email : String? = null
    var password :String? = null
    var confirmpassword:String?=null
    var name :String?=null
    var authListener:AuthListener?=null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if  (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Email or Password")
            return
        }

        Coroutines.main {
            try{
                var authResponse = repository.userLogin(email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }catch(e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view:View){
        Intent(view.context, LoginActivity::class.java).also{
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View){
        authListener?.onStarted()
        if  (email.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty()){
            authListener?.onFailure("All form must be filled!")
            return
        }else if(password != confirmpassword){
            authListener?.onFailure("Password didnt match!")
            return
        }

        Coroutines.main {
            try{
                var authResponse = repository.userSignup(email!!,password!!,name!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }catch(e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}