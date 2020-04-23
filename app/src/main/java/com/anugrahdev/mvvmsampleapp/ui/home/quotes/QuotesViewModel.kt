package com.anugrahdev.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.anugrahdev.mvvmsampleapp.data.repositories.QuotesRepository
import com.anugrahdev.mvvmsampleapp.utils.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}
