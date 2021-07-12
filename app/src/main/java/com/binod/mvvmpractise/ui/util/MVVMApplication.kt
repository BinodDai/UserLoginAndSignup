package com.binod.mvvmpractise.ui.util

import android.app.Application
import com.binod.mvvmpractise.data.network.Api
import com.binod.mvvmpractise.data.network.NetworkConnectionInterceptor
import com.binod.mvvmpractise.data.repo.UserRepository
import com.binod.mvvmpractise.db.AppDatabase
import com.binod.mvvmpractise.ui.auth.AuthViewModel
import com.binod.mvvmpractise.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication:Application(),KodeinAware {
    override val kodein= Kodein.lazy{
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}