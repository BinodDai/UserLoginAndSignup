package com.binod.mvvmpractise.ui.auth

import androidx.lifecycle.LiveData
import com.binod.mvvmpractise.db.entities.User

interface AuthListner {

    fun onStart()
    fun onSuccess(user:User)
    fun onFailure(message:String)
}