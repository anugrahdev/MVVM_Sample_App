package com.anugrahdev.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.anugrahdev.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}