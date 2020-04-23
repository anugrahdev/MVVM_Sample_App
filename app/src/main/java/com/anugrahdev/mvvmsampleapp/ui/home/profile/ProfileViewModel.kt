package com.anugrahdev.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.anugrahdev.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    val user = repository.getUser()
}
