package com.anugrahdev.mvvmsampleapp

import android.app.Application
import com.anugrahdev.mvvmsampleapp.data.db.AppDatabase
import com.anugrahdev.mvvmsampleapp.data.network.ApiService
import com.anugrahdev.mvvmsampleapp.data.network.NetworkInterceptor
import com.anugrahdev.mvvmsampleapp.data.repositories.QuotesRepository
import com.anugrahdev.mvvmsampleapp.data.repositories.UserRepository
import com.anugrahdev.mvvmsampleapp.preferences.PreferenceProviders
import com.anugrahdev.mvvmsampleapp.ui.auth.AuthViewModelFactory
import com.anugrahdev.mvvmsampleapp.ui.home.profile.ProfileViewModel
import com.anugrahdev.mvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import com.anugrahdev.mvvmsampleapp.ui.home.quotes.QuotesViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from singleton { PreferenceProviders(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
    }

}