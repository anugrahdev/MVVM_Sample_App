package com.anugrahdev.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anugrahdev.mvvmsampleapp.data.db.AppDatabase
import com.anugrahdev.mvvmsampleapp.data.db.entities.Quote
import com.anugrahdev.mvvmsampleapp.data.network.ApiService
import com.anugrahdev.mvvmsampleapp.data.network.SafeApiRequest
import com.anugrahdev.mvvmsampleapp.preferences.PreferenceProviders
import com.anugrahdev.mvvmsampleapp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit


private val INTERVAL = 6;
class QuotesRepository(private val api:ApiService,
                       private val db:AppDatabase,
                       private val prefs: PreferenceProviders
)
    : SafeApiRequest() {
    private val  quotes = MutableLiveData<List<Quote>>()

    init {
        // Live data akan di save ke dalam lokal database
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    //fetch the quotes
    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getLastSavedAt()
        if(lastSavedAt==null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            //fetch the quotes from api
            val response= apiRequest { api.getQuotes() }
            //post quotes value to the quotes live data
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(SavedAt: LocalDateTime):Boolean{
        return ChronoUnit.HOURS.between(SavedAt, LocalDateTime.now()) > INTERVAL
    }




    private fun saveQuotes(quotes: List<Quote>){
        //save quotes data to local database
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

}