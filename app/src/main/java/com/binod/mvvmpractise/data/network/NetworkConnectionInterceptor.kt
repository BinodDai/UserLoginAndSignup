package com.binod.mvvmpractise.data.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.binod.mvvmpractise.ui.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context):Interceptor {

    private val applicationContext=context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("No internet Connection")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable():Boolean
    {
        val connectivityManager=applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       connectivityManager.activeNetworkInfo.also {
           return it !=null && it.isConnected
       }
    }
}